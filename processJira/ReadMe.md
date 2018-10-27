

Local Development Set-up:

1. Build simple node js server to mock Jira Service
2. Build graze/sqs-local as local docker image
3. Start Spring Boot APP to server end-point /rest/api/2/search?q={search_query}


1. Ensure Docker is running on your machine.
2. ./docker-setup.sh 
3. After the command finishes there should two image created (and already running).
        atlassian_sqs_1
        atlassian_JiraService_1

4. Start Java App
    mvn spring-boot:run -Dspring.profiles.active=default -DJIRA_BASE_URL=http://localhost:5000 -DQUEUE_URL=http://localhost:9324


