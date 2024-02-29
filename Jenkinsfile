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
             stage("init"){
                            steps{
                                script{
                                    gv = load "script.groovy"
                                }
                            }
                        }

            stage("build"){
                steps{
                    script{
                            gv.buildApp()
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
                        gv.testApp()
                      }
                }
            }

            stage("deploy"){
                steps{
                        script{
                            gv.deployApp()
                        }
                  }
             }

        }

}