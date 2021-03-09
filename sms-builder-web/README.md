# Build
mvn clean package && docker build -t co.edu.utp.gia.sms/sms-builder-web .
 

# RUN

docker rm -f sms-builder-web || true && docker run -d -p 8080:8080 -p 4848:4848 -p 3306:3306 --name sms-builder-web co.edu.utp.gia.sms/sms-builder -web 
