def funcs

pipeline {
    agent any

    stages {
        stage('Load Functions') {
            steps {
                script {
                    funcs = load 'functions.groovy'
                }
            }
        }

        stage('Initialize') {
            steps {
                script {
                    funcs.initHelm()
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    funcs.deployWithHelm()
                }
            }
        }
    }
}
