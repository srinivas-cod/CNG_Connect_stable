# How to Run CNG Connect Backend

This is a Spring Boot application.

## Prerequisites
1.  **Java 17 Development Kit (JDK 17)** must be installed.
    *   Verify with: `java -version`
2.  **Maven** must be installed (since the wrapper is missing).
    *   Verify with: `mvn -version`
    *   Install from: https://maven.apache.org/download.cgi

## Running the Application

Open a terminal in this directory and run:

```bash
mvn spring-boot:run
```

Or, to build a JAR file:

```bash
mvn clean install
java -jar target/cng-backend-1.0.0.jar
```

## Troubleshooting
-   **"mvn is not recognized"**: You need to install Maven and add it to your system PATH.
-   **"Address already in use"**: Make sure no other application is running on port 8080.
-   **MongoDB Errors**: Check `src/main/resources/application.properties` to ensure the database URI is correct and your IP is allowed in MongoDB Atlas.
