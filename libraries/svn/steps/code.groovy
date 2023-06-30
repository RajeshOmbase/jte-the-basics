
// Fetch code from svn repository
void call()
{
    node {
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
           def currentDir = sh(returnStdout: true, script: 'pwd').trim()
            println "env starts"
           // sh  current_dir + '/dmifactory'
            //def localStoragePath = env.current_dir
            println "inspect code starts"
            // env.LOCAL_STORAGE_PATH = currentDir
            // sh "cp -R ${localStoragePath} ${env.WORKSPACE}"
            def sourceFolder = currentDir + '/dmifactory'
            // show content of sourceFolder/dmifactory
            sh "ls -l ${sourceFolder}"
            println "inspect code intermediate"
            // Stash the folder structure and data
            stash name: 'myStash', includes: "sourceFolder"
            print "inspect code ends"
            // // Stash the folder structure and data
            // stash name: 'myStash', includes: sourceFolder
            // // show content in myStash
            // println "inspect code ends"

            // Store the local storage path in an environment variable
           // env.LOCAL_STORAGE_PATH = localStoragePath
          
            
        // Set the path to the build.xml file

        
    

}

    }

        

}
