
FROM maven:3.8.3-openjdk-17 AS build
COPY opportunity_management_system/ .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/opportunity_management_system-0.0.1-SNAPSHOT.jar OpportunityManagementSystem.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","OpportunityManagementSystem.jar" ]
