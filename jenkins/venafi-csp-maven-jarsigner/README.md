# venafi-csp-maven-jarsigner

This maven plugin provides the capability to sign and verify a project artifact and attachments using jarsigner.  As such this allows for a quick integration with Venafi CodeSign Protect:

Review [Maven Jarsigner Plugin](https://maven.apache.org/plugins/maven-jarsigner-plugin/index.html) documentation for usage information

### Important files to review:
* [Jenkinsfile](Jenkinsfile) - Note that we bootstrap the maven image with the CSP client
* [pom.xml](pom.xml) - Review jarsigner plugin section to show how to leverage the Venafi CSP compatible jarsigner arguments

