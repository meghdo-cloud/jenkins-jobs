// Define the relative path to gitrepos.txt inside the repository
def repoFile = readFileFromWorkspace('seed_jobs/gitrepos.txt')

// Read the git repository list
def gitRepos = repoFileContent.split('\n')

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
