void call(){
node {
    def tools = [ant: 'Ant_Home']
    stage("Maven: Build"){
        println "build from the maven library"	
        

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

                remote: 'https://svn.riouxsvn.com/dmifactory']],

        workspaceUpdater: [$class: 'UpdateUpdater']])

        script {

            def currentDir = sh(returnStdout: true,script: 'pwd').trim()

        // Print current directory

            echo "Current directory: ${currentDir}"

        // List contents of the current directory

            sh "ls ${currentDir}"

        //var = echo 'Current directory: ' + pwd()

            sh 'ls /home/jenkins/agent/workspace/jte-job/dmifactory'

            //def tools = [ant: 'Ant_Home']
            def buildXmlPath = "${env.WORKSPACE}/dmifactory/build.xml"
            def buildXmlContent = readFile("${env.WORKSPACE}/dmifactory/build.xml")
            echo "Build.xml Content:\n${buildXmlContent}"
            //def antHome = tool 'Ant_Home'
            withAnt(installation: 'Ant_Home') {
                def currentDir1 = sh(returnStdout: true,script: 'pwd').trim()
                echo "Current directory: ${currentDir1}"
                sh "ls ${currentDir1}"
                sh 'ls -l'
                sh 'ant clean compile jspDeploy target war'
                
            // Your build steps here    
            
    }

        }

    // Set the path to the build.xml file



}




        }		
    
}
