#!/bin/sh
mvn clean package && docker build -t co.edu.utp.gia.sms/sms-builder-web .
docker rm -f sms-builder-web || true && docker run -d -p 8080:8080 -p 4848:4848 --name sms-builder-web co.edu.utp.gia.sms/sms-builder-web

docker logs sms-builder-web

open http://localhost:8080/sms-builder-web/revision/seleccionarRevision.xhtml
