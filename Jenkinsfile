pipeline {
    agent {   label 'jnlpagent'    }
    triggers {
        githubPush()
      }
    stages {
        stage('Run Seed Job DSL') {
            steps {
                jobDsl targets: 'seed_jobs/seed_job.groovy', removedJobAction: 'IGNORE'
            }
        }
  
    }
}
