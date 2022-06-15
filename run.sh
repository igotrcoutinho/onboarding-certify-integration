#!/bin/bash

mvn clean package -DskipTests &&
docker-compose rm -f &&
docker-compose up --build