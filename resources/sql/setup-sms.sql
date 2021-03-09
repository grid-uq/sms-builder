create database sms;
CREATE USER 'sms'@'%' IDENTIFIED BY 'sms-12345';
GRANT ALL PRIVILEGES ON sms.* TO 'sms'@'%';
FLUSH PRIVILEGES;

