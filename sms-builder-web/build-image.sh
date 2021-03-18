#!/bin/bash
docker rmi sms-builder:latest
sh ./sms-builder-web-uberjar.sh
docker build -t sms-builder:latest .
docker tag sms-builder christiancandela/sms-builder
#docker push christiancandela/sms-builder
