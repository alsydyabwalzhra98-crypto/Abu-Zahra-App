#!/usr/bin/env sh
APP_HOME=$(pwd)
APP_NAME="Gradle"
warn () { echo "$*" ; }
die () { echo "$*" ; exit 1 ; }
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
exec java -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
