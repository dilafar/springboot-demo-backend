
def buildApp(){
    echo "build the pipeline in ..."
    echo "build version ${NEW_VERSION}..."
    sh "mvn install"
}
def testApp(){
    echo "test the pipeline..."
}
def deployApp(){
    echo "deploy the pipeline..."
    echo "deploying version ${params.VERSION}"
}

return this