#!/bin/bash

docker rmi griduq/sms-builder
sh ./sms-builder-web-uberjar.sh
docker build -t griduq/sms-builder .
