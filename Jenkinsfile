pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "demo-test"
        CONTAINER_NAME = "demo-container"  // Define a unique container name
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t demo-test .'
                }
            }
        }

        stage('Run Container') {
            steps {
                script {
                    // Set a timeout to prevent indefinite execution
                    timeout(time: 5, unit: 'MINUTES') {  // Adjust the time as needed
                        bat "docker run -t --name ${CONTAINER_NAME} ${DOCKER_IMAGE}"
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Stop and remove the container if it is still running
                bat "docker stop ${CONTAINER_NAME} || true"
                bat "docker rm ${CONTAINER_NAME} || true"
            }
            cleanWs()  // Clean workspace after each run
        }
    }
}

