#!/bin/sh
mvn clean package 
java -jar /Users/cacandela/servers/payara-micro-5.2020.2.jar --deploy ./target/smsgia-web.war --outputUberJar ./target/smsgia-web.jar