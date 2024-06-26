pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Cloner le dépôt Git public
                    git url: 'https://github.com/Thyb12/AsteroidTrackerIntegrationRendu'
                }
            }
        }
        stage('Build') {
            steps {
                // Commandes pour construire le projet, par exemple pour un projet Java
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Commandes pour exécuter les tests unitaires
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                // Commandes pour empaqueter le projet, par exemple en .jar ou Docker image
                sh 'mvn package'
            }
        }

        stage('Archive Artifacts') {
             steps {
                 // Archiver les artefacts générés pour les conserver
                 archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
        }
    }
}
pipeline {
    agent any

    tools {
        maven 'Maven3' // Assurez-vous que le nom correspond à l'installation Maven configurée dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm // Récupère le code source du repository GitHub
            }
        }

        stage('Build') {
            steps {
                script {
                    // Compile le projet avec Maven
                    def mvnHome = tool 'Maven3'
                    sh "${mvnHome}/bin/mvn clean install"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Exécute les tests unitaires
                    def mvnHome = tool 'Maven3'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    // Package le projet dans un format approprié (jar, war, etc.)
                    def mvnHome = tool 'Maven3'
                    sh "${mvnHome}/bin/mvn package"
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                // Archive le package généré et d'autres artefacts
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Ajoutez ici vos étapes de déploiement, par exemple, déploiement sur un serveur Tomcat
            }
        }
    }
}