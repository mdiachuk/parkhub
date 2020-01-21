#!/bin/bash

echo "Generating JDBC Properties file"
sed -e "s;%GC_INSTANCE%;$1;g" -e "s;%GC_DB_NAME%;$2;g" \
    -e "s;%DB_USER%;$3;g" -e "s;%DB_PASS%;$4;g" \
    src/main/webapp/WEB-INF/appengine-web.xml.template > src/main/webapp/WEB-INF/appengine-web.xml
