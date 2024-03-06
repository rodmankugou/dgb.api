#/bin/bash

PRO_PARAM="$@"

BIZ_JAR='/root/projects/sevensea.api/biz/biz-service-provider/target/biz-service-1.0-SNAPSHOT-exec.jar'
USER_JAR='/root/projects/sevensea.api/base/base-user-service/target/base-user-service-1.0-SNAPSHOT-exec.jar'
WEBAPI_JAR='/root/projects/sevensea.api/apis/web-api/target/web-api-1.0-SNAPSHOT-exec.jar'
ADMIN_JAR='/root/projects/sevensea.api/apis/admin-api/target/admin-api-1.0-SNAPSHOT-exec.jar'


echo "package:$PRO_PARAM"
cd /root/projects/sevensea.api
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
cp $USER_JAR   ./user-nft-test.jar
cp $BIZ_JAR   ./biz-nft-test.jar
cp $WEBAPI_JAR   ./web-nft-test.jar
cp $ADMIN_JAR   ./admin-nft-test.jar



if [[ ${PRO_PARAM} =~ biz ]]
then
    echo 'run biz...'
    ps aux|grep biz-nft-test|awk '{print $2}'|xargs kill -9
    sleep 5
    nohup java -jar -Xmx256m biz-nft-test.jar > biz.out &
fi

if [[ ${PRO_PARAM} =~ user ]]
then
    echo 'run user...'
    ps aux|grep user-nft-test|awk '{print $2}'|xargs kill -9
    sleep 3
    nohup java -jar -Xmx256m user-nft-test.jar >user.out &
fi


if [[ ${PRO_PARAM} =~ web ]]
then
    echo 'run web-api...'
    ps aux|grep web-nft-test|awk '{print $2}'|xargs kill -9
    sleep 10
    nohup java -jar -Xmx256m web-nft-test.jar >web.out &
fi

if [[ ${PRO_PARAM} =~ admin ]]
then
    echo 'run admin-api...'
    ps aux|grep admin-nft-test|awk '{print $2}'|xargs kill -9
    sleep 10
    nohup java -jar -Xmx256m  admin-nft-test.jar >admin.out &
fi
