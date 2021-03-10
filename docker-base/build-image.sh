#!/bin/bash
docker rmi sms-builder-db-jdk
docker build -t sms-builder-db-jdk:latest .
