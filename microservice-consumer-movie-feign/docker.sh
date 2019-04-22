#!/bin/bash

./mvnw install -DskipTests
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
pwd
docker build -t microservice-consumer-movie-feign .