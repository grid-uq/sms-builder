mvn clean
mvn package 
java -D --add-opens java.base/java.io=ALL-UNNAMED -D --add-exports java.base/jdk.internal.misc=ALL-UNNAMED -jar target/sms-builder-web-microbundle.jar --nocluster


