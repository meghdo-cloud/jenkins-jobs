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
                // Define the relative path to gitrepos.txt inside the repository
                    def repoFile = new File("gitrepos.txt")
                    
                    // Read the git repository list
                    def gitRepos = repoFile.readLines()
                    
                    gitRepos.each { repoUrl ->
                    
                        def repoName = repoUrl.split('/').last().replace('.git', '')
                    
                        multibranchPipelineJob(repoName) {
                            description("Multibranch pipeline for ${repoName}")
                    
                            branchSources {
                                git {
                                    id(repoName) // Unique identifier for the repository
                                    remote(repoUrl) // Repository URL
                                    credentialsId('jenkins_agent_ssh') // SSH credentials ID
                                    includes('*') // Tracks all branches; modify if necessary
                                }
                            }
                    
                            orphanedItemStrategy {
                                discardOldItems {
                                    daysToKeep(10)
                                    numToKeep(5)
                                }
                            }
                    
                            factory {
                                workflowBranchProjectFactory {
                                    scriptPath('Jenkinsfile') 
                                }
                            }
                        }
                    
                        println "Created multibranch pipeline job for ${repoName}"
                    }
            }
        }
  
    }
}
