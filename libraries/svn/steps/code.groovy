// Fetch code from svn repository
void call()
{
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

        // env.current_dir = sh(returnStdout: true, script: 'pwd').trim()
        //     println "env starts"
        //    // sh  current_dir + '/dmifactory'
        //     def localStoragePath = env.current_dir
        //     println "inspect code starts"

        //     // Store the local storage path in an environment variable
        //     env.LOCAL_STORAGE_PATH = localStoragePath
        //     println "inspect code ends"
        // Set the path to the build.xml file
        
    

}

    }
