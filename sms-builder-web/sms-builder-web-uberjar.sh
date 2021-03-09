#!/bin/sh
mvn clean package 
java -jar /Users/cacandela/servers/payara-micro-5.2020.2.jar --deploy ./target/sms-builder-web.war --outputUberJar ./target/sms-builder-web.jar
