libraries{
  rsvn
  {
svn_repo = "https://svn.riouxsvn.com/dmifactory"
}
  rant
  rsonarqube
  {
    projectKey = "CalculatorApp"
    sonarHostUrl = "http://ec2-35-83-131-175.us-west-2.compute.amazonaws.com"
  }
  rjfrog
  {
    artifactory_global_id = "artifactory-server"
    artifactory_url = "http://ec2-44-234-63-153.us-west-2.compute.amazonaws.com/artifactory"
    artifactory_credentials = "jfrog_credentials"
    artifactory_repo = "java_sample_app"
}
  rtomcat
  {
    tomcat_global_id = "tomcat_deployment_cred"
    tomcat_url = "bitnami@ec2-35-83-251-194.us-west-2.compute.amazonaws.com"
    tomcat_war_deploy_path = "/opt/bitnami/tomcat/webapps"
    tomcat_shutdown_path = "/opt/bitnami/tomcat/bin/shutdown.sh"
    tomcat_startup_path = "/opt/bitnami/tomcat/bin/startup.sh"
  }

}
