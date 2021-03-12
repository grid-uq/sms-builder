FROM mariadb AS sms-builder-db-jdk
LABEL version="1.0"
LABEL description="Image with MariaDB and Open JDK 11"
#ENV MYSQL_ROOT_PASSWORD=12345
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get -y --no-install-recommends install openjdk-11-jdk && rm -rf /var/lib/apt/lists/*
COPY setup-sms.sql /docker-entrypoint-initdb.d/
COPY smsbuilderweb.sh .
EXPOSE 8080
EXPOSE 3306
RUN /usr/local/bin/docker-entrypoint.sh

CMD ["sh","smsbuilderweb.sh"]

