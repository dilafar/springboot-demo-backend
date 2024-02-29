
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
            stage("build"){
                steps{
                    script{
                            echo "build the pipeline in ..."
                            echo "build version ${NEW_VERSION}..."
                            sh "mvn install"
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
                        echo "test the pipeline..."
                }
            }

         stage("deploy"){
             steps{
                        echo "deploy the pipeline..."
                        echo "deploying version ${params.VERSION}"
                  }
             }

        }

}