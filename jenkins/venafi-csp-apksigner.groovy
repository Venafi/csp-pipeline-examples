pipeline {
    agent {
        docker { 
            image 'zosocanuck/venafi-csp-openjdk:8-jdk'
            args '-u root'
        }
    }
    stages {
        stage("Download Resource") {
            steps {
                sh 'wget -nv https://my.repo.com/artifacts/test.apk -O ~/test.apk'
            }
        }
        stage("Venafi CodeSign Protect") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'signer-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh '/opt/venafi/codesign/bin/pkcs11config getgrant --force --authurl=$CSP_AUTH_URL --hsmurl=$CSP_HSM_URL --username=$USERNAME --password=$PASSWORD'
                    sh 'wget https://my.repo.com/venafipkcs11.txt -O ~/venafipkcs11.txt'
                    sh 'apksigner sign --ks NONE --ks-pass "pass:test" --provider-class sun.security.pkcs11.SunPKCS11 --provider-arg ~/venafipkcs11.txt --ks-type PKCS11 --ks-key-alias my-production-cert ~/test.apk'
                }
            }
        }
    }
}