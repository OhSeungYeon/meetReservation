APPICATION_NAME=GBMSender
REPOSITORY=/home/ec2-user/build
APP_DIRECTORY=/home/ec2-user/app

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f $APPICATION_NAME)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    sudo kill -15 $CURRENT_PID
    sudo sleep 5
fi

echo "> 새 어플리케이션 배포"
echo "> Build 파일 복사"

mkdir -p $APP_DIRECTORY
sudo rm -rf $APP_DIRECTORY/*
sudo cp $REPOSITORY/*.jar $APP_DIRECTORY/

sudo rm -rf /etc/init.d/start-$APPICATION_NAME.sh
sudo cp /home/ec2-user/build/start-app.sh /etc/init.d/start-$APPICATION_NAME.sh