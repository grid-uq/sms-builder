# Build
mvn clean package && docker build -t co.edu.utp.gia.sms/smsgia-web .
 

# RUN

docker rm -f smsgia-web || true && docker run -d -p 8080:8080 -p 4848:4848 --name smsgia-web co.edu.utp.gia.sms/smsgia-web 