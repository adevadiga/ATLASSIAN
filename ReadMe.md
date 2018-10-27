

Local Development Set-up:

The feature has been developed as a Spring Boot APP exposing Rest end-point at /rest/api/2/search?q={search_query}`

A sample Node JS server is run as mock Jira end-point locally.
graze/sqs-local is run as as mock SQS end-point.
Both are run as docker containers.


1. Ensure Docker is running on your machine.
2. Run ./docker-setup.sh 
3. After the command finishes there should two image created (and already running).

                atlassian_sqs_1
                atlassian_JiraService_1

4. Start Spring Boot App
    mvn spring-boot:run -Dspring.profiles.active=default -DJIRA_BASE_URL=http://localhost:5000 -          DQUEUE_URL=http://localhost:9324



Spring profile is being used to run different environment set-up.
Spring profile "default" assumes locally running Jira and SQS service.
Where as Spring profile "prod" runs as a prod environment set-up.
