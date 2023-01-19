pipeline {
    agent {
        docker { 
            alwaysPull true
            image 'zosocanuck/venafi-csp-cosign:ubuntu'
            args '-u root'
        }
    }
    environment {
        GITHUB_PAT = credentials('github_pat')
        CSP_HSM_URL = 'https://tpp.example.com/vedhsm'
        CSP_AUTH_URL = 'https://tpp.example.com/vedauth'
    }
    stages {
        stage("Venafi") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'signer-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh '/opt/venafi/codesign/bin/pkcs11config getgrant --force --authurl=$CSP_AUTH_URL --hsmurl=$CSP_HSM_URL --username=$USERNAME --password=$PASSWORD'
                    sh 'docker login ghcr.io -u myaccount -p $GITHUB_PAT'
                    sh 'cosign sign --key "pkcs11:slot-id=0;object=Production?module-path=/opt/venafi/codesign/lib/venafipkcs11.so&pin-value=1234" ghcr.io/myorg/sample-image@sha256:521311a0923441ac832704e7b9fe4daa0a04685a01c9cf54ff3382fd1cde9411'
                }
            }
        
        }
    }
}