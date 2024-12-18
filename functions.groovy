        stage('Run Tests') {
            steps {
                sh 'npm test' // הרצת בדיקות
            }
        }

        stage('Initialize Helm') {
            steps {
                script {
                    funcs.initializeHelm() // התקנה והגדרה של Helm
                }
            }
        }

        stage('Deploy with Helm') {
            steps {
                script {
                    funcs.deployWithHelm() // פריסת האפליקציה עם Helm
                }
            }
        }
    }
}

// פונקציות גנריות
def installGoogleCloudSDK() {
    sh '''
        echo "Installing Google Cloud SDK"
        curl https://sdk.cloud.google.com | bash
        exec -l $SHELL // טעינת הגדרות לאחר ההתקנה
    '''
}

def authenticateWithGoogleCloud() {
    sh '''
        echo "Authenticating with Google Cloud"
        gcloud auth activate-service-account --key-file=$GOOGLE_CREDENTIALS
        gcloud config set project YOUR_PROJECT_ID // יש להחליף בפרטי הפרויקט שלך
    '''
}

def authenticateWithGKE() {
    sh '''
        echo "Authenticating with GKE"
        gcloud container clusters get-credentials YOUR_CLUSTER_NAME --zone YOUR_CLUSTER_ZONE --project YOUR_PROJECT_ID
    '''
}

def initializeHelm() {
    sh '''
        echo "Installing Helm CLI"
        helm version || curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
    '''
}

def deployWithHelm() {
    sh '''
        echo "Deploying with Helm"
        helm repo add stable https://charts.helm.sh/stable
        helm install my-release stable/nginx-ingress
    '''
}

return this
