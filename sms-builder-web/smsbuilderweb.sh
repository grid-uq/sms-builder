/usr/local/bin/docker-entrypoint.sh mysqld &
java -jar sms-builder-web.jar --port 8080
