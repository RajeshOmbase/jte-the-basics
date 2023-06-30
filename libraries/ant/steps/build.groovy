void call()
{
    //def tools = [ant: 'Ant_Home']
    stage("Ant: Build"){
        String svn_repo = config.svn_repo
        println "build from the Ant library"
        String currentDir = env.current_dir
        echo "Current directory: ${currentDir}"
        sh "ls ${currentDir}"

        script {
           // def currentDir = System.getProperty("user.dir")
            

            //def currentDir = sh(returnStdout: true,script: 'pwd').trim()

        // Print current directory

           // echo "Current directory: ${currentDir}"

        // List contents of the current directory

            //sh "ls ${currentDir}"

            //def tools = [ant: 'Ant_Home']
            def buildXmlPath = "${currentDir}/dmifactory/build.xml"
            def buildXmlContent = readFile("${currentDir}/dmifactory/build.xml")
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
    

