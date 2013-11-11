#!/bin/bash

TOMCAT_HOME_DIR=/opt/dev/evalua/server/cam/apache-tomcat-6.0.20
TOMCAT_BIN=$TOMCAT_HOME_DIR/bin
PROJECT_HOME=/opt/dev/evalua/workspace/cam
TOMCAT_WEBAPP=$TOMCAT_HOME_DIR/webapps
TARGET_WEBAPP=$PROJECT_HOME/target/cam


cd $TOMCAT_BIN
  ./catalina.sh stop

cd $PROJECT_HOME
mvn clean

mvn compile war:exploded

if [ ! -L $TOMCAT_WEBAPP/ROOT ]; then
cd $TOMCAT_WEBAPP
ln -s $TARGET_WEBAPP ROOT
fi
cd $TOMCAT_BIN
  ./catalina.sh run


