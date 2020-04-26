# Quarkus QuickStart Project - Simple Blog ![Java CI with Gradle](https://github.com/ElinaValieva/quarkus-blog-app/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)
> This project uses Quarkus, the Supersonic Subatomic Java Framework.

&nbsp;
## Prerequisites :heavy_exclamation_mark:
 1. `JDK 11` at least - for running app
 2. `Gradle 6+` - for building package

&nbsp;
## Quarkus lifecycle :hammer:
#### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
./gradlew quarkusDev
```

#### Packaging and running the application
The application can be packaged using ./gradlew quarkusBuild. It produces the blog-1.0.0-SNAPSHOT-runner.jar file in the build directory.
```
./gradlew quarkusBuild

java -jar build/blog-1.0.0-SNAPSHOT-runner.jar
```

#### Creating a native executable
Using GraalVM:
```
./gradlew buildNative
```
Without GraalVM:
```
./gradlew buildNative --docker-build=true
```
#### OpenApi
Swagger Api available by path `/api`

#### Health check
Apllication health check available by path `/health`

&nbsp;
## Docker build :whale:
```console
./gradlew  quarkusBuild
```
Specify Dockerfile `native` or `jvm` build in command parameter with option `-f` and run image
```console
docker build -t quarkus-blog-api:jvm -f src/main/docker/Dockerfile.jvm .

docker run -i --rm -p 8080:8080 quarkus-blog-api:jvm
```

&nbsp;
## Openshift deployment :triangular_flag_on_post:
Deployment using **s2i**:
```console
oc new-app quay.io/quarkus/ubi-quarkus-native-s2i:19.3.1-java11~https://github.com/ElinaValieva/quarkus-quickstarts.git \
              --context-dir=. --name=quarkus-blog-api
              
oc expose svc/quarkus-blog-api
```
Deployment using **docker image**:
```console
oc new-app elvaliev/quarkus-blog-api:latest
oc expose svc/quarkus-blog-api
```


&nbsp;
## AWS deployment :large_orange_diamond:
For native build use - sam.native.yaml
```console
sam local start-api --template sam.jvm.yaml

sam package --template-file sam.jvm.yaml --output-template-file packaged.yaml --s3-bucket <YOUR_S3_BUCKET>

sam deploy --template-file packaged.yaml --capabilities CAPABILITY_IAM --stack-name <YOUR_STACK_NAME>
```
