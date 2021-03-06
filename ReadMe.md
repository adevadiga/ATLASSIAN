

## Running application locally:

The feature has been developed as a Spring Boot APP exposing Rest end-point at /api/issue/sum?query={search_query}&name={descriptive_name}`

http://localhost:8080/api/issue/sum?query=aaa&name=bb

A sample Node JS server is run as mock Jira end-point locally.
graze/sqs-local is run as as mock SQS end-point.
Both are run as docker containers.


1. Ensure Docker is running on your machine.
2. Run ./docker-setup.sh 
3. After the command finishes there should two image created (and already running).

                atlassian_sqs_1
                atlassian_JiraService_1

4. Start Spring Boot App

            mvn spring-boot:run -Dspring.profiles.active=default
              -DJIRA_BASE_URL=http://localhost:5000 -DQUEUE_URL=http://localhost:9324



In order to segregate parts of application configuration and make it be available only in certain environments spring profiles are used.
Spring profile "default" assumes locally running Jira and SQS service.
Where as Spring profile "prod" assumes working Jira & SQS QueueUrl end-point.


## Running application as in prod:

mvn spring-boot:run -Dspring.profiles.active=prod -DJIRA_BASE_URL=<JiraBaseURL> -DQUEUE_URL=<SQSQueueURL>
