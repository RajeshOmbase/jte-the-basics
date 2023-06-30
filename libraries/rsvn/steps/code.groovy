package libraries.sdp

import hudson.AbortException

// Fetch code from svn repository
@Validate
void call(context)
{
        node{
        stage('svn:code') {
            String svn_repo = config.svn_repo
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
                            remote: svn_repo]],
                workspaceUpdater: [$class: 'UpdateUpdater']])
            sh 'ls -l'

           def currentDir = sh(returnStdout: true,script: 'pwd').trim()
            
        // Print current directory

            echo "Current directory: ${currentDir}"

        // List contents of the current directory
            
			//dir("${currentDir}/dmifactory")
			dir('dmifactory'){
			sh 'ls -l'
			stash name: 'workspace', allowEmpty: true, useDefaultExcludes: false
			//create_workspace_stash(name: 'my-artifacts', includes: '/home/jenkins/agent/workspace/Rajesh-JTE-Pipeline/dmifactory/**')
			//create_workspace_stash('my-artifacts') 
            //dir('/home/jenkins/agent/workspace/Rajesh-JTE-Pipeline/dmifactory') 
            //stash(includes: '**', name: 'my-artifacts')
    

			
            sh "ls ${currentDir}"
        
    

}

        }

}
}