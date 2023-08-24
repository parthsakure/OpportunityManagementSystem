FROM openjdk:17.0.1-jdk-slim
EXPOSE 8080
ENTRYPOINT [ "java","-jar","OpportunityManagementSystem.jar" ]