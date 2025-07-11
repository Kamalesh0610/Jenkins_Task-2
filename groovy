pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Kamalesh0610/Jenkins_Task-2.git'
            }
        }
        
        stage('Build') {
            steps {
                sh './simple_script.sh'
            }
        }
    }
    
    post {
        always {
            emailext (
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                body: """
                Build: ${env.JOB_NAME} - ${env.BUILD_NUMBER}
                Status: ${currentBuild.currentResult}
                
                Check console output at ${env.BUILD_URL}console
                
                ${currentBuild.changeSets}
                """,
                to: "kamaleshk085@gmail.com"
            )
        }
    }
}
