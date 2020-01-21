#!/bin/bash

echo "Generating JDBC Properties file"
sed -i -e "s;%GC_INSTANCE%;$1;g" src/main/webapp/WEB-INF/appengine-web.xml
sed -i -e "s;%GC_DB_NAME%;$2;g" src/main/webapp/WEB-INF/appengine-web.xml
sed -i -e "s;%DB_USER%;$3;g" src/main/webapp/WEB-INF/appengine-web.xml
sed -i -e "s;%DB_PASS%;$4;g" src/main/webapp/WEB-INF/appengine-web.xml
