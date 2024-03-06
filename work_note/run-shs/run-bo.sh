#/bin/bash

PRO_PARAM="$@"

DIST='/hone/ubuntu/projects/sevensea-bo/dist'


echo "package:$PRO_PARAM"
cd hone/ubuntu/projects/sevensea-bo
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

cd "/mnt/sevensea/static"
rm -rf admin
cp $DIST  admin
