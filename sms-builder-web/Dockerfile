
# 1st stage, build the app
FROM maven:3.6-openjdk-17 as build

WORKDIR /app

# Create a first layer to cache the "Maven World" in the local repository.
# Incremental docker builds will always resume after that, unless you update
# the pom
ADD pom.xml .
RUN mvn package -Dmaven.test.skip -Declipselink.weave.skip

# Do the Maven build!
# Incremental docker builds will resume here when you change sources
ADD src src
RUN mvn package -DskipTests
RUN echo "done!"

# 2nd stage, build the runtime image
#FROM openjdk:21-ea-17-slim
#FROM bellsoft/liberica-openjdk-alpine:17
FROM bellsoft/liberica-openjre-alpine:17
WORKDIR /app

# Copy the binary built in the 1st stage
COPY --from=build /app/target/sms-builder-web-microbundle.jar ./

CMD ["java", "-jar", "sms-builder-web-microbundle.jar"]

EXPOSE 8080
