pipeline {
    agent {
        docker {
            image 'gradle:7.5.1-jdk11' 
            args '-u root -v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Venafi') {
            steps {
                sh '''#!/bin/bash
                       apt-get update
                       apt-get install --no-install-recommends -y wget ca-certificates
                       wget https://$CSP_DOMAIN/csc/clients/venafi-csc-latest-x86_64.deb
                       wget https://$CSP_DOMAIN/poc_guide/venafipkcs11.txt -O /root/venafipkcs11.txt
                       dpkg -i venafi-csc-latest-x86_64.deb
                       /opt/venafi/codesign/bin/pkcs11config getgrant --force --authurl=$CSP_AUTH_URL --hsmurl=$CSP_HSM_URL --username=$CSP_USER --password=$CSP_PASSWORD
                   '''
            }
        }
        stage('Build') { 
            steps {
                sh 'gradle build'
                sh 'gradle sign' 
            }
        }
    }
}