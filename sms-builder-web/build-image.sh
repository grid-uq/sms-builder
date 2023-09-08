#!/bin/bash
fecha=$(date '+%Y.%m.%d')
projectVersion=$(mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)
version="$projectVersion.$fecha"
docker rmi griduq/sms-builder
docker buildx build --platform linux/amd64,linux/arm64 -t griduq/sms-builder -t griduq/sms-builder:$version  --push -f Dockerfile .

