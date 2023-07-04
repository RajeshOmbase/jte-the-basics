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
                println "deploying to tomcat starts"
                sh "scp -o StrictHostKeyChecking=no **/*.war bitnami@ec2-35-83-251-194.us-west-2.compute.amazonaws.com:/opt/bitnami/tomcat/webapps"
                println "deploying to tomcat mid"
                sh 'ssh -o StrictHostKeyChecking=no bitnami@ec2-35-83-251-194.us-west-2.compute.amazonaws.com "/opt/bitnami/tomcat/bin/shutdown.sh"'
                println "deploying to tomcat mid1"
                sh 'ssh -o StrictHostKeyChecking=no bitnami@ec2-35-83-251-194.us-west-2.compute.amazonaws.com "/opt/bitnami/tomcat/bin/startup.sh"'
                println "deploying to tomcat mid2"
                // sh "scp -o StrictHostKeyChecking=no **/*.war ${tomcat_url}:/tmp"
                // println "deploying to tomcat mid"
                // sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/bitnami/tomcat/bin/shutdown.sh"
                // println "deploying to tomcat mid1"
                // sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} /opt/bitnami/tomcat/bin/startup.sh"
                                 
                    }
            
        }
    }
}