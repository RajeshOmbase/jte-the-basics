// Fetch code from svn repository


void call()
{
    node 
{
    println "start agent"
    label 'jenkins-slave'
    print "hello"
    agent {
            // Replace with the label of your Jenkins slave
    }
    stage('svn:code') {
        String svn_repo = config.svn_repo
                 // Checkout code from SVN repository changes
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
        // Set the path to the build.xml file
        
    

}

}
}