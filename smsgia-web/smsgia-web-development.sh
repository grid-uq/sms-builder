#!/bin/sh
mvn clean package && docker build -t co.edu.utp.gia.sms/smsgia-web .
docker rm -f smsgia-web || true && docker run -d -p 8080:8080 -p 4848:4848 -v target:/opt/payara/deployments --name smsgia-web co.edu.utp.gia.sms/smsgia-web 
docker logs smsgia-web