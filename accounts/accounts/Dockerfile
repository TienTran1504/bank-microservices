# Use the Eclipse Temurin JDK 21 base image
FROM eclipse-temurin:21-jdk

# Information about the maintainer of this image
MAINTAINER tdtien.work@gmail.com

# Add the application JAR file to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts.jar

# Set the entry point to run the application
ENTRYPOINT ["java","-jar","/accounts.jar"]
