// Path to the git repository list
def repoFile = new File("${WORKSPACE}/gitrepos.txt")
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
                includes('*') // Tracks all branches; modify if necessary (e.g., to only track specific branches)
            }
        }

        // Add orphaned branch strategy to keep jobs for a specified period before deleting
        orphanedItemStrategy {
            discardOldItems {
                daysToKeep(10) // Keep for 30 days; adjust as needed
                numToKeep(5) // Keep only the last 20 builds; adjust as needed
            }
        }

        // Define the Jenkinsfile location within the repository (root-level Jenkinsfile)
        factory {
            workflowBranchProjectFactory {
                scriptPath('Jenkinsfile') 
            }
        }
    }

    println "Created multibranch pipeline job for ${repoName}"
}
