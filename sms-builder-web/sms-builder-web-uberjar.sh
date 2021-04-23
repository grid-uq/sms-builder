#!/bin/sh
mvn clean package 
java -jar ~/.m2/repository/fish/payara/extras/payara-micro/5.2021.2/payara-micro-5.2021.2.jar --deploy ./target/sms-builder-web.war --outputUberJar ./target/sms-builder-web.jar
