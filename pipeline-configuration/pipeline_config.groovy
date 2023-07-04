libraries{
    //merge = true
    svn 
    {
        svn_repo = "https://svn.riouxsvn.com/dmifactory"
    }
    ant
    sonarqube
    {
        projectKey = "CalculatorApp"
        sonarHostUrl = "http://ec2-35-83-131-175.us-west-2.compute.amazonaws.com"

    }
    //artifactory. take reference from buildcombined.groovy
    jfrog 
    {
        artifactory_global_id = "artifactory-server"
        artifactory_url = "http://ec2-44-234-63-153.us-west-2.compute.amazonaws.com/artifactory"
        artifactory_credentials = "jfrog_credentials"
        artifactory_repo = "java_sample_app"
    }
    tomcat
    {
        tomcat_global_id = "tomcat_deployment_cred"
        tomcat_url = "bitnami@ec2-35-83-251-194.us-west-2.compute.amazonaws.com"
        tomcat_war_deploy_path = "/opt/bitnami/tomcat/webapps"
        tomcat_shutdown_path = "/opt/bitnami/tomcat/bin/shutdown.sh"
        tomcat_startup_path = "/opt/bitnami/tomcat/bin/startup.sh"
    }
}

allow_scm_jenkinsfile = true 


application_environments{
    dev{
        ip_addresses = [ "0.0.0.1", "0.0.0.2" ]
    }
    prod{
        long_name = "Production"
        ip_addresses = [ "0.0.1.1", "0.0.1.2", "0.0.1.3", "0.0.1.4" ]
    }
}
