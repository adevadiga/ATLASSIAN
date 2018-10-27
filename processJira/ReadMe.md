
mvn package && java -jar target/processJira-0.0.1-SNAPSHOT.jar


mvn spring-boot:run -Dspring.profiles.active=default -DJIRA_BASE_URL=http://localhost:5000 -DQUEUE_URL=default