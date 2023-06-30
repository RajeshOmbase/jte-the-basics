void call()
{
    String projectKey = config.projectKey
    String sonarHostUrl = config.sonarHostUrl
    println "static code analysis from the sonarqube library and test1" 
    script 
    {
        echo "SonarQube analysis"
        def scannerHome = tool 'SonarQubeScanner'
        withSonarQubeEnv('SonarQubeScanner') 
        {
            echo "Current directory: ${currentDir}"
            sh "ls ${currentDir}"
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.host.url=${sonarHostUrl} -Dsonar.sources=${currentDir}/dmifactory/src  -Dsonar.java.binaries=${currentDir}/dmifactory/build -Dsonar.log.level=DEBUG"

        }        

            }   
      

        }



