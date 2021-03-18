#!/bin/bash
docker rmi sms-builder-db-jdk
docker build -t sms-builder-db-jdk:latest .
docker tag sms-builder-db-jdk christiancandela/sms-builder-db-jdk
#docker push christiancandela/sms-builder-db-jdk
