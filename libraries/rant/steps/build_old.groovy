package libraries.sdp

import hudson.AbortException

void call()
{   
    node{
    //def another_script = load 'jte-the-basics/libraries/ant/steps/build.groovy'
    def tools = [ant: 'Ant_Home']
    stage("Ant: Build"){
        script
        {
            println "build from the Ant library"
			
            def currentDir = sh(returnStdout: true,script: 'pwd').trim()
            echo "Current directory: ${currentDir}"
            unstash name: 'workspace'
			sh "ls ${currentDir}"
            //def buildXmlPath = "${env.WORKSPACE}/dmifactory/build.xml"
			def buildXmlPath = "${env.WORKSPACE}/build.xml"
            //def buildXmlContent = readFile("${env.WORKSPACE}/dmifactory/build.xml")
			def buildXmlContent = readFile("${env.WORKSPACE}/build.xml")
            echo "Build.xml Content:\n${buildXmlContent}"
            //def antHome = tool 'Ant_Home'
            withAnt(installation: 'Ant_Home') {
                //dir("${currentDir}/dmifactory")
				dir("${currentDir}") {
                    sh 'ant clean compile jspDeploy target war'
                } 
                
        }
        }

       

       }


}

}    
