pipeline {
    agent {   label 'javaagent'    }
    stages {
        stage('Checkout Seed Script') {
            steps {
                // Checkout the repository containing the seed job script and gitrepos.txt
                git url: 'git@github.com:your-org/your-repo.git', branch: 'main', credentialsId: 'jenkins_agent_ssh'
            }
        }
        stage('Run Seed Job DSL') {
            steps {
                // Run the Job DSL script and point to the groovy file
                sh 'ls -R'
                jobDsl targets: 'seed_jobs/seed_job.groovy', removedJobAction: 'IGNORE'
            }
        }
        post {
          always {
              script {
                  cleanWs()
              }
          }  
      }     
    }
}
