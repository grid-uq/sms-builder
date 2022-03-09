#!/bin/sh
mvn clean package
mv target/sms-builder-web-microbundle.jar target/sms-builder-web.jar
#java -jar ~/.m2/repository/fish/payara/extras/payara-micro/5.2021.9/payara-micro-5.2021.9.jar --deploy ./target/sms-builder-web.war --outputUberJar ./target/sms-builder-web.jar
