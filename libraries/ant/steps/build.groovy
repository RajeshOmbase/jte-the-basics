void call(){
    node
{
    //def tools = [ant: 'Ant_Home']
    stage("Ant: Build"){
        String svn_repo = config.svn_repo
        println "build from the Ant library"
		//echo ${env.JOB_NAME}	
        sh 'echo $JOB_NAME'
	      

// Checkout code from SVN repository changes

        checkout([$class: 'SubversionSCM',

        additionalCredentials: [],

        excludedCommitMessages: '',

        excludedRegions: '',

        excludedRevprop: '',    

        excludedUsers: '',

        filterChangelog: false,

        ignoreDirPropChanges: false,

        includedRegions: '',

        locations: [[credentialsId: 'svn_credential_pipeline',

                depthOption: 'infinity',

                ignoreExternalsOption: true,

                remote: svn_repo]],

        workspaceUpdater: [$class: 'UpdateUpdater']])

        script {

            def currentDir = sh(returnStdout: true,script: 'pwd').trim()

        // Print current directory

            echo "Current directory: ${currentDir}"

        // List contents of the current directory

            sh "ls ${currentDir}"

        //var = echo 'Current directory: ' + pwd()

            //sh 'ls /home/jenkins/agent/workspace/CI-Job/dmifactory'

            //def tools = [ant: 'Ant_Home']
            def buildXmlPath = "${env.WORKSPACE}/dmifactory/build.xml"
            def buildXmlContent = readFile("${env.WORKSPACE}/dmifactory/build.xml")
            echo "Build.xml Content:\n${buildXmlContent}"
            //def antHome = tool 'Ant_Home'
            withAnt(installation: 'Ant_Home') {
                dir("${currentDir}/dmifactory") {
                    sh 'ant clean compile jspDeploy target war'
                }

            }

            //def currentDir = sh(returnStdout: true,script: 'pwd').trim()
            String projectKey = config.projectKey
            String sonarHostUrl = config.sonarHostUrl

            println "static code analysis from the sonarqube library and test1" 
            script {
                echo "SonarQube analysis"
                def scannerHome = tool 'SonarQubeScanner'
                withSonarQubeEnv('SonarQubeScanner') {
                    echo "Current directory: ${currentDir}"
                    sh "ls ${currentDir}"
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.host.url=${sonarHostUrl} -Dsonar.sources=${currentDir}/dmifactory/src  -Dsonar.java.binaries=${currentDir}/dmifactory/build -Dsonar.log.level=DEBUG"

                        // dmifactory/build
                        //-Dsonar.log
                        
                    }          

            }

            // Configure Artifactory server using Jenkins Artifactory Plugin
            String artifactoryUrl = config.artifactory_url
            String artifactory_credentials = config.artifactory_credentials
            String artifactory_global_id = config.artifactory_global_id
            String artifactory_repo = config.artifactory_repo
            rtServer (
                id: artifactory_global_id,
                url: artifactory_url,
                credentialsId: artifactory_credentials
            )   

            

            rtUpload (
                    serverId: artifactory_global_id,
                    spec: {
                        files{
                            file{
                                pattern  "**/*.war",
                                target  artifactory_repo
                            }
                        }
                            
                        
                    }
                )








                //def currentDir1 = sh(returnStdout: true,script: 'pwd').trim()
                //echo "Current directory: ${currentDir1}"
                //sh "ls ${currentDir1}"
                //sh 'ls -l'
                
                
                
            // Your build steps here    
            
    }

        }

    // Set the path to the build.xml file



}

}
    

