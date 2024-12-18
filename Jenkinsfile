def funcs

pipeline {
    agent any
    environment {
        DOCKER_REGISTRY = 'rubberrachel'
        DOCKER_IMAGE = "${DOCKER_REGISTRY}/node-mongo-app"
        KUBECONFIG_CREDENTIALS = credentials('kubeconfig-id') // קובץ ההסמכה לקוברנטס
        GOOGLE_CREDENTIALS = credentials('google-cloud-json-key') // קובץ ההסמכה ל-Google Cloud
    }

    stages {
        stage('Load Functions') {
            steps {
                script {
                    funcs = load 'functions.groovy' // טוען את הפונקציות
                }
            }
        }

        stage('Install Google Cloud SDK') {
            steps {
                script {
                    funcs.installGoogleCloudSDK() // התקנה של ה-Google Cloud SDK
                }
            }
        }

        stage('Authenticate with Google Cloud') {
            steps {
                script {
                    funcs.authenticateWithGoogleCloud() // התחברות עם המפתח JSON ל-Google Cloud
                }
            }
        }

        stage('Authenticate with GKE') {
            steps {
                script {
                    funcs.authenticateWithGKE() // התחברות לקוברנטס ב-GKE
                }
            }
        }

        stage('Clone Repository') {
            steps {
                git 'https://github.com/cdcdlsk/mongo.git' // שיכפול מאגר Git
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'npm install' // התקנת תלויות npm
            }
        }
