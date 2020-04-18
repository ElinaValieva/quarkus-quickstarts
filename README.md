# Quarkus QuickStart Project - Simple Blog ![Java CI with Gradle](https://github.com/ElinaValieva/quarkus-blog-app/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)
> This project uses Quarkus, the Supersonic Subatomic Java Framework.

&nbsp;
## Prerequisites :heavy_exclamation_mark:
 1. `JDK 11` at least - for running app
 2. `Gradle 6+` - for building package

&nbsp;
## Running the application in dev mode :arrow_forward:

You can run your application in dev mode that enables live coding using:

```
./gradlew quarkusDev
```

&nbsp;
## Packaging and running the application :hammer:
The application can be packaged using ./gradlew quarkusBuild. It produces the blog-1.0.0-SNAPSHOT-runner.jar file in the build directory.
```
./gradlew quarkusBuild

java -jar build/blog-1.0.0-SNAPSHOT-runner.jar
```

&nbsp;
## Creating a native executable :electric_plug:
Using GraalVM:
```
./gradlew buildNative
```
Without GraalVM:
```
./gradlew buildNative --docker-build=true
```

&nbsp;
## Openshift deployment :triangular_flag_on_post:
```console
oc new-app quay.io/quarkus/ubi-quarkus-native-s2i:19.3.1-java11~https://github.com/Elina/quarkus-quickstarts.git \
              --context-dir=. --name=quarkus-quickstart-native
              
oc expose svc/quarkus-quickstart-native
```
