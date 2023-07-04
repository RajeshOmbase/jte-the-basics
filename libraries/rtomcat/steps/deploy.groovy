package libraries.sdp
import hudson.AbortException
void call()
{
    node
    {
        // deploy war file to tomcat
        stage('deploy:tomcat') {
            unstash name: 'workspace'
            String tomcat_url = config.tomcat_url
            sshagent(['tomcat_deployment_cred']) {
                def currentDir = sh(returnStdout: true,script: 'pwd').trim()
                sh "ls  ${currentDir}"
                sh "scp -o StrictHostKeyChecking=no **/*.war ${tomcat_url}:/tmp"
                println "deploying to tomcat mid"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/bitnami/tomcat/bin/shutdown.sh"
                println "deploying to tomcat mid1"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/bitnami/tomcat/bin/startup.sh"
                                 
                    }
            
        }
    }
}