call()
{   println "static code analysis from the sonarqube library and test"
    stage("SonarQube: Static Code Analysis")
    {
        def currentDir = sh(returnStdout: true,script: 'pwd').trim()
        String projectKey = config.projectKey
        String sonarHostUrl = config.sonarHostUrl

        println "static code analysis from the sonarqube library and test1" 
        script {
            echo "SonarQube analysis"
            def scannerHome = tool 'SonarQubeScanner'
            withSonarQubeEnv('sonarserver') {
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.host.url=${sonarHostUrl} -Dsonar.sources=src -Dsonar.java.binaries=${currentDir}/dmifactory/build -Dsonar.log.level=DEBUG"

                    // dmifactory/build
                    //-Dsonar.log
                    
                }           

        }

}


    }

