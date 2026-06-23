#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Maven2 Start Up Shell Script
#
# Required ENV vars:
# ------------------
#   JAVA_HOME - location of a JDK home dir
#
# Optional ENV vars:
# ------------------
#   MAVEN_OPTS - parameters passed to the Java VM when running Maven
#     e.g. to debug Maven itself, use
#       set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
# ----------------------------------------------------------------------------

set -e

PRG="$0"
while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
PRGDIR="`pwd`"
cd "$SAVED" >/dev/null

# find the project's root directory
MAVEN_PROJECTBASEDIR="$PRGDIR"
while [ ! -d "$MAVEN_PROJECTBASEDIR/.mvn" ] && [ "$MAVEN_PROJECTBASEDIR" != "/" ]; do
  MAVEN_PROJECTBASEDIR=`dirname "$MAVEN_PROJECTBASEDIR"`
done

if [ "$MAVEN_PROJECTBASEDIR" = "/" ]; then
  MAVEN_PROJECTBASEDIR="$PRGDIR"
fi

CLASSPATH="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
exec java $MAVEN_OPTS -classpath "$CLASSPATH" "-Dmaven.multiModuleProjectDirectory=$MAVEN_PROJECTBASEDIR" org.apache.maven.wrapper.MavenWrapperMain "$@"
