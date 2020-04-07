rm -rf /Users/luixip/servers/payara5/glassfish/domains/domain1/autodeploy/*
mvn clean 
mvn package 
cp target/smsgia-web.war /Users/luixip/servers/payara5/glassfish/domains/domain1/autodeploy/
