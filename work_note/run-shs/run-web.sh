#/bin/bash

PRO_PARAM="$@"

DIST='/hone/ubuntu/projects/sevenseas-web-mandarin/dist'


echo "package:$PRO_PARAM"
cd hone/ubuntu/projects/sevenseas-web-mandarin
git pull origin 'online'

if [ $? -ne 0 ]; then
    echo "git pull failed"
    exit
else
    echo "git pull succeed"
fi

npm run build
if [ $? -ne 0 ]; then
    echo "npm failed"
    exit
else
    echo "npm succeed"
fi

cd "/mnt/cn-sevensea/static"
rm -rf web web.zip
cp $DIST  web
