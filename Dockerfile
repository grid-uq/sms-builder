FROM mariadb
ENV DEBIAN_FRONTEND=noninteractive
ENV MYSQL_ROOT_PASSWORD=12345
RUN apt-get update
RUN apt-get -y install openjdk-11-jdk
USER root:root
RUN pwd
COPY ../resources/sql/setup-sms.sql .
#RUN mysql -u root -p12345 < setup-sms.sql
COPY ./target/sms-builder-web.jar .
COPY smsbuilderweb.sh .
#COPY smsbuilderweb.sh /docker-entrypoint-initdb.d
EXPOSE 8080
EXPOSE 3306
#CMD ["sh","apacheforegroud.sh"]
#FROM payara/server-full
#COPY ./target/sms-builder-web.war ${DEPLOY_DIR}
#VOLUME ./target /opt/payara/deployments
# comentario
