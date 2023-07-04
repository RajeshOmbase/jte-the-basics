package libraries.sdp

import hudson.AbortException
void call()
{
    node 
    {
        stage('integrate:jfrog') {
            unstash name: 'workspace_build'
            String artifactory_url = config.artifactory_url
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
                    spec: '''{
                        "files": [
                            {
                                "pattern": "**/*.war",
                                "target": "java_sample_app"
                            }
                        ]
                    }'''
                )

        }

         
    }
}