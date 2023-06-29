void call(){
    def tools = [ant: 'Ant_Home']
    stage("Ant: Build"){
        println "build from the Ant library"
        script {

            def currentDir = sh(returnStdout: true,script: 'pwd').trim()

        // Print current directory

            echo "Current directory: ${currentDir}"

        // List contents of the current directory

            sh "ls ${currentDir}"
            def buildXmlPath = "${env.WORKSPACE}/dmifactory/build.xml"
            def buildXmlContent = readFile("${env.WORKSPACE}/dmifactory/build.xml")
            echo "Build.xml Content:\n${buildXmlContent}"
            //def antHome = tool 'Ant_Home'
            withAnt(installation: 'Ant_Home') {
                dir("${currentDir}/dmifactory") {
                    sh 'ant clean compile jspDeploy target war'
                } 
            
    }

        }


}

}
    
