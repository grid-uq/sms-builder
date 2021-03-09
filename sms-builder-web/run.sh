rm -rf /Users/luixip/servers/payara5/glassfish/domains/domain1/autodeploy/*
mvn clean 
mvn package 
cp target/sms-builder-web.war /Users/luixip/servers/payara5/glassfish/domains/domain1/autodeploy/
open -a safari http://localhost:8080/sms-builder-web/

