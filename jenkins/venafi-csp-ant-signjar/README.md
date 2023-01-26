# venafi-csp-ant-signjar

The following project demonstrates how Java application signing can be introduced into the Ant build process. Leveraging an off-the-shelf plugin for signing jars within an Ant build process allows for a quick integration with Venafi CodeSign Protect:

Review [Ant Signjar Plugin](https://ant.apache.org/manual/Tasks/signjar.html) documentation for usage information

### Important files to review:
* [Jenkinsfile](Jenkinsfile) - Note that we bootstrap the maven image with the CSP client as well as the latest version of [ant](https://ant.apache.org/).
* [maven-build.xml](maven-build.xml) - Review the signjar section to show how to leverage the Venafi CSP compatible jarsigner arguments