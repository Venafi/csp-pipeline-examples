FROM alpine:3.5@sha256:f7d2b5725685826823bc6b154c0de02832e5e6daf7dc25a00ab00f1158fabfc8

#Create Ant Dir
RUN mkdir -p /opt/ant/
#Download And 1.9.8
RUN wget --https-only --secure-protocol=TLSv1_2 https://archive.apache.org/dist/ant/binaries/apache-ant-1.9.8-bin.tar.gz -P /opt/ant && \
    echo "ca31bd42c27f7e63cccb219ee59930fdc943ca9e0d2b20e8f3e8ec0ee7b02346  /opt/ant/apache-ant-1.9.8-bin.tar.gz" | sha256sum -c -
#Unpack Ant
RUN tar -xvzf /opt/ant/apache-ant-1.9.8-bin.tar.gz -C /opt/ant/
# Remove tar file
RUN rm -f /opt/ant/apache-ant-1.9.8-bin.tar.gz
#Drop Sonarqube lib
RUN wget --https-only --secure-protocol=TLSv1_2 https://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/sonar-ant-task/2.3/sonar-ant-task-2.3.jar -P /opt/ant/apache-ant-1.9.8/lib/ && \
    echo "c96fd09dfd0ff9907d3467de5fe17b3fb9f3c95a74afaa4021d80b5da436f61e  /opt/ant/apache-ant-1.9.8/lib/sonar-ant-task-2.3.jar" | sha256sum -c -
#Install JDK 1.8
RUN apk --update add openjdk8
#Install GIT
RUN apk --update add git
#Install Curl
RUN apk --update add curl
#Setting Ant Home
ENV ANT_HOME=/opt/ant/apache-ant-1.9.8
#Setting Ant Params
ENV ANT_OPTS="-Xms256M -Xmx512M"
#Updating Path
ENV PATH="${PATH}:${HOME}/bin:${ANT_HOME}/bin"
