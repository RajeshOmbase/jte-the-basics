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
                sh "scp -o StrictHostKeyChecking=no **/*.war ${tomcat_url}:/opt/bitnami/tomcat/webapps"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/bitnami/tomcat/bin/shutdown.sh"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/bitnami/tomcat/bin/startup.sh"
                                 
                    }
            
        }
    }
}