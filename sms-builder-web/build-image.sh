#!/bin/bash

docker rmi griduq/sms-builder
docker build -t griduq/sms-builder .
