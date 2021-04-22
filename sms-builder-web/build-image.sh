#!/bin/bash

fecha=$(date '+%Y.%m.%d')
projectVersion=$(mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)
version="$projectVersion.$fecha"
echo $version
docker rmi sms-builder:latest
sh ./sms-builder-web-uberjar.sh
docker build -t sms-builder:latest .
docker tag sms-builder griduq/sms-builder
docker tag griduq/sms-builder griduq/sms-builder:$version
docker push griduq/sms-builder
docker push griduq/sms-builder:$version
