void call()
{
    node
    {
        def currentDir = sh(returnStdout: true,script: 'pwd').trim()
        unstash 'myStash'
        def sourceFolder = currentDir
        // check the contents of the stash
        sh "ls ${sourceFolder}"

        // def localStoragePath = env.LOCAL_STORAGE_PATH
        // println "Current directory path: ${localStoragePath}"
    // print data from the current directory
        // sh "ls ${localStoragePath}"
        // println "above code executed"
//     void processCurrentDirectory(String currentDir) {
//     // Perform operations using the current directory path
//     println "Current directory: ${currentDir}"
// }
    //def tools = [ant: 'Ant_Home']
        stage("Ant: Build"){
            String svn_repo = config.svn_repo
            println "build from the Ant library"
            String currentDir = env.current_dir
            echo "Current directory: ${currentDir}"
        //def localStoragePath = env.LOCAL_STORAGE_PATH
        //sh "ls ${localStoragePath}"
        //sh "ls ${currentDir}"

            script {

           // def currentDir = System.getProperty("user.dir")
            

            //def currentDir = sh(returnStdout: true,script: 'pwd').trim()

        // Print current directory

           // echo "Current directory: ${currentDir}"

        // List contents of the current directory

            //sh "ls ${currentDir}"

            //def tools = [ant: 'Ant_Home']
                println "start build"
                def buildXmlPath = currentDir + "/dmifactory/build.xml"
                echo "Build.xml Path: ${buildXmlPath}"
                def buildXmlContent = readFile(buildXmlPath)
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
    

        }
    

