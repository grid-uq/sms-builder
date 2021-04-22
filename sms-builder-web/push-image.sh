#!/bin/bash

fecha=$(date '+%Y.%m.%d')
projectVersion=$(mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)
version="$projectVersion.$fecha"
sh ./build-image.sh
docker tag griduq/sms-builder griduq/sms-builder:$version
docker push griduq/sms-builder
docker push griduq/sms-builder:$version
