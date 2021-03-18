#!/bin/sh
docker rm -f sms-builder || true && docker run -e MYSQL_ROOT_PASSWORD=12345 -d -p 3306:3306 -p 8080:8080 --name sms-builder christiancandela/sms-builder
docker logs sms-builder
open http://localhost:8080/sms-builder-web/
