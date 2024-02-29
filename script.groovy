
def buildApp(){
    echo "build the pipeline in ..."
    echo "build version ${NEW_VERSION}..."
    sh "mvn install"
}
def testApp(){
    echo "test the pipeline..."
}
def buildImage(){
    withCredentials([
            usernamePassword(credentialsId:'docker-credentials',usernameVariable:'USER',passwordVariable:'PASS')
    ]){
        sh "docker build -t fadhiljr/mssample:java-maven-2.0 ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh "docker push fadhiljr/mssample:java-maven-2.0"

    }
}
def deployApp(){
    echo "deploy the pipeline..."
    echo "deploying version ${params.VERSION}"
}

return this