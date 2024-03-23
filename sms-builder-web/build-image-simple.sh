#!/bin/bash
fecha=$(date '+%Y.%m.%d')
projectVersion=$(mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)
version="$projectVersion.$fecha"
docker rmi griduq/sms-builder
export DOCKER_CLI_BUILD=1
export DOCKER_BUILDKIT=1
#docker buildx create --name sms-buildx --use
docker buildx use sms-buildx
docker buildx build --platform linux/amd64,linux/arm64 -t griduq/sms-builder -t griduq/sms-builder:$version  --push -f src/main/docker/Dockerfile.jvm-micro .

