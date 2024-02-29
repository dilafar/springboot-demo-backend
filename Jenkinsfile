#!usr/bin/env groovy

library identifier: "jenkins-shared-library@master" , retriever: modernSCM([
        $class: "GitSCMSource",
        remote: "https://github.com/dilafar/jenkins-shared-library.git",
        credentialsId: "git-credentials",
])
def gv
pipeline{
        agent any
        environment{
            NEW_VERSION = "1.3.0"
        }
        tools{
            maven 'maven'
        }
        parameters{
            choice(name: 'VERSION',choices:["1.1.0","1.1.1","1.1.2"],description:'select version')
            booleanParam(name:'executeTests',defaultValue:true,description: '')
        }

        stages{
            stage("increment version"){
                steps{
                    script{
                        incrementPatchVersion()
                        def version = readFile('pom.xml')=~ '<version>(.+)</version>'
                        def build_version = version[0][1]
                        env.IMAGE_NAME = "${build_version}-${BUILD_NUMBER}"
                    }
                }
            }
            stage("build"){
                steps{
                    script{
                            buildApp()
                    }
                }
            }

            stage("test"){
                when{
                 expression{
                        params.executeTests
                    }
                }
                steps{
                      script{
                        testApp()
                      }
                }
            }

            stage("build and push image"){
                            steps{
                                  script{
                                      buildImage("fadhiljr/mssample:${IMAGE_NAME}")
                                      dockerLogin()
                                      dockerPush("fadhiljr/mssample:${IMAGE_NAME}")
                                  }
                            }
             }

            stage("deploy"){
                steps{
                        script{

                            deployApp()

                        }
                  }
             }

        }

}