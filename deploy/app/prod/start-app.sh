#!/bin/bash

APPICATION_NAME=GBMSender
REPOSITORY=/home/ec2-user/build
APP_DIRECTORY=/home/ec2-user/app

echo "> 어플리케이션을 시작합니다."

JAR_NAME=$(ls $APP_DIRECTORY/ |grep $APPICATION_NAME | tail -n 1)

echo "> JAR Name: $APP_DIRECTORY/$JAR_NAME & $JAR_NAME"

sudo nohup java -jar -Dspring.profiles.active=prod $APP_DIRECTORY/$JAR_NAME --publicKey=prod2020prod2020 >/dev/null 2>&1 &
cd /home/McsAgent
sudo sh startAgent.sh
#sudo nohup java -jar -Dspring.profiles.active=prod $APP_DIRECTORY/$JAR_NAME --publicKey=prod2020prod2020 --serverNo=1 >/dev/null 2>&1 &
#sudo nohup java -jar -Dspring.profiles.active=prod $APP_DIRECTORY/$JAR_NAME --publicKey=prod2020prod2020 --serverNo=2 >/dev/null 2>&1 &