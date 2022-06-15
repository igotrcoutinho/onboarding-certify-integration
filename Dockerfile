FROM openjdk:8-jdk-alpine

WORKDIR /app

RUN apk add --update curl && rm -rf /var/cache/apk/*

COPY target/kappta-unico-service.jar .

RUN (cd /opt ; apk add curl ; curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip ; unzip newrelic-java.zip)

COPY newrelic.yml /opt/newrelic/newrelic.yml

ENTRYPOINT ["java", "-javaagent:/opt/newrelic/newrelic.jar", "-jar", "/app/kappta-unico-service.jar"]