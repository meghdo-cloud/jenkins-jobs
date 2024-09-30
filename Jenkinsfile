// Read the git repository list from an external file
def repoFile = new File("${WORKSPACE}/gitrepos.txt")
def gitRepos = repoFile.readLines()

gitRepos.each { repoUrl ->

    def repoName = repoUrl.split('/').last().replace('.git', '')

    pipelineJob(repoName) {
        description("Pipeline for ${repoName}")
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                        }
                        branches('*/main') 
                        extensions {}
                    }
                }
                scriptPath('Jenkinsfile') 
            }
        }
        triggers {
            scm('H/5 * * * *') // Poll SCM every 5 minutes. Adjust as needed.
        }
    }

    println "Created pipeline job for ${repoName}"
}
