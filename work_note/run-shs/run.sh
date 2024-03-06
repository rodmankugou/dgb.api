#/bin/bash

PRO_PARAM="$@"

BIZ_JAR='/root/projects/yastart.api/biz/biz-service-provider/target/biz-service-1.0-SNAPSHOT-exec.jar'
USER_JAR='/root/projects/yastart.api/base/base-user-service/target/base-user-service-1.0-SNAPSHOT-exec.jar'
WEBAPI_JAR='/root/projects/yastart.api/apis/web-api/target/web-api-1.0-SNAPSHOT-exec.jar'
ADMIN_JAR='/root/projects/yastart.api/apis/admin-api/target/admin-api-1.0-SNAPSHOT-exec.jar'


echo "package:$PRO_PARAM"
cd /root/projects/yastart.api
git pull origin 'master'

if [ $? -ne 0 ]; then
    echo "git pull failed"
    exit
else
    echo "git pull succeed"
fi

mvn clean package -P test -Dmaven.test.skip=true
if [ $? -ne 0 ]; then
    echo "mvn failed"
    exit
else
    echo "mvn succeed"
fi

cd "/mnt/yastart_test/apis"
rm -rf ./*.jar
cp $USER_JAR   ./user-test.jar
cp $BIZ_JAR   ./biz-test.jar
cp $WEBAPI_JAR   ./web-test.jar



if [[ ${PRO_PARAM} =~ biz ]]
then
    echo 'run biz...'
    ps aux|grep biz-test|awk '{print $2}'|xargs kill -9
    sleep 5
    nohup java -jar biz-test.jar > biz.out &
fi

if [[ ${PRO_PARAM} =~ user ]]
then
    echo 'run user...'
    ps aux|grep user-test|awk '{print $2}'|xargs kill -9
    sleep 3
    nohup java -jar user-test.jar >user.out &
fi


if [[ ${PRO_PARAM} =~ web ]]
then
    echo 'run web-api...'
    ps aux|grep web-test|awk '{print $2}'|xargs kill -9
    sleep 10
    nohup java -jar web-test.jar >web.out &
fi
