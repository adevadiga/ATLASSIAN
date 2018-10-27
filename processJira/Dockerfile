FROM java:openjdk-8u91-jdk
ADD processJira/target/processJira-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080:8080
CMD echo "Starting application..." && \
java -Xms2048m -Xmx2048m -Djava.security.egd=file:/dev/./urandom -jar -Dfile.encoding=UTF8 /app.jar