void call()
{
    node {
    String projectKey = config.projectKey
    String sonarHostUrl = config.sonarHostUrl
    println "static code analysis from the sonarqube library and test1" 
    script 
    {
        echo "SonarQube analysis"
        def scannerHome = tool 'SonarQubeScanner'
		def currentDir = sh(returnStdout: true,script: 'pwd').trim()
		//echo "Current directory: ${currentDir}"
		unstash name: 'workspace_build'
		//echo "Current directory: ${currentDir}"
        withSonarQubeEnv('SonarQubeScanner') 
        {
            echo "Current directory: ${currentDir}"
            sh "ls ${currentDir}"
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.host.url=${sonarHostUrl} -Dsonar.sources=${currentDir}/src  -Dsonar.java.binaries=${currentDir}/build -Dsonar.log.level=DEBUG"

        }        

            }   
      

        }

}

