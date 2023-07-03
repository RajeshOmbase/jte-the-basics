void call()
{
    node
    {
        stage('deploy:tomcat') {
            String tomcat_url = config.tomcat_url
            sshagent(['tomcat_deployment_cred']) {
                println "deploying to tomcat starts"
                def currentDir = sh(returnStdout: true,script: 'pwd').trim()
                sh "ls  ${currentDir}/dmifactory"
                println "deploying to tomcat mid"
                sh "scp -o StrictHostKeyChecking=no **/*.war ${tomcat_url}:/opt/tomcat/webapps"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/tomcat/bin/shutdown.sh"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/tomcat/bin/startup.sh"
                                 
                    }
            
        }
    }
}