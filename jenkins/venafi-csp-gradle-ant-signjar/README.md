# venafi-csp-gradle-ant-signjar

The following project demonstrates how Java application signing can be introduced into the Gradle build process. Leveraging an off-the-shelf Ant plugin for signing jars within a Gradle build process allows for quick integration with Venafi CodeSign Protect:

Review [Ant Signjar Plugin](https://ant.apache.org/manual/Tasks/signjar.html) documentation for usage information

### Important files to review:
* [Jenkinsfile](Jenkinsfile) - Note that we bootstrap the Gradle image with the CSP client.
* [build.gradle](build.gradle) - Review the sign task to show how to leverage the Venafi CSP compatible jarsigner arguments