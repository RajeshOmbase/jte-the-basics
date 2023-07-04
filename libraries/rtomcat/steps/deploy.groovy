package libraries.sdp
import hudson.AbortException
//import groovy.json.JsonSlurper
void call()
{
    node
    {
        // deploy war file to tomcat
        stage('deploy:tomcat') {
            unstash name: 'workspace_build'
            String tomcat_global_id = config.tomcat_global_id
            String tomcat_url = config.tomcat_url
            String tomcat_war_deploy_path = config.tomcat_war_deploy_path
            String tomcat_shutdown_path = config.tomcat_shutdown_path
            String tomcat_startup_path = config.tomcat_startup_path
            sshagent([${tomcat_global_id}]) {
                def currentDir = sh(returnStdout: true,script: 'pwd').trim()
                sh "ls  ${currentDir}"
                println "deploying to tomcat starts"
                sh "scp -o StrictHostKeyChecking=no **/*.war ${tomcat_url}:${tomcat_war_deploy_path}"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} ${tomcat_shutdown_path}"
                sh "ssh -o StrictHostKeyChecking=no ${tomcat_url} ${tomcat_startup_path}"
                println "deploying to tomcat ends"
                                 
                    }
            
        }
    }
}