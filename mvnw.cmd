@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Maven2 Start Up Batch script
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars
@REM MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM MAVEN_BATCH_PAUSE - set to 'on' to wait for a key stroke before ending
@REM MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM ----------------------------------------------------------------------------

@echo off
@setlocal

if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if exist "%HOME%\mavenrc_pre.bat" call "%HOME%\mavenrc_pre.bat"

set ERROR_CODE=0

@REM To isolate internal variables from possible side effects, we use a prefix.
@REM At the same time, we need to be careful not to create too many variables
@REM because of the 8192 character limit on the length of a command line.

set "MAVEN_PROJECTBASEDIR=%~dp0"
if "%MAVEN_PROJECTBASEDIR:~-1%"=="\" set "MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%"

@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".
@REM Fallback to current working directory if not found.

set "MAVEN_WPR_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR%"
:findBaseDir
if exist "%MAVEN_WPR_PROJECTBASEDIR%\.mvn" goto baseDirFound
set "MAVEN_WPR_PROJECTBASEDIR=%MAVEN_WPR_PROJECTBASEDIR%\.."
if exist "%MAVEN_WPR_PROJECTBASEDIR%" goto findBaseDir

set "MAVEN_WPR_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR%"
:baseDirFound

set "MAVEN_WPR_CONFIG_DIR=%MAVEN_WPR_PROJECTBASEDIR%\.mvn\wrapper"

set "MAVEN_WPR_JAR=%MAVEN_WPR_CONFIG_DIR%\maven-wrapper.jar"
set "MAVEN_WPR_PROPERTIES=%MAVEN_WPR_CONFIG_DIR%\maven-wrapper.properties"
set "MAVEN_WPR_CLASS=org.apache.maven.wrapper.MavenWrapperMain"

if not exist "%MAVEN_WPR_JAR%" (
    echo Couldn't find %MAVEN_WPR_JAR%, please run with a version of Maven that supports the wrapper.
    exit /b 1
)

@REM JAVA_HOME is required
if not "%JAVA_HOME%" == "" goto checkJava

echo.
echo Error: JAVA_HOME is set to an invalid directory.
echo JAVA_HOME = "%JAVA_HOME%"
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.
echo.
goto error

:checkJava
set "JAVACMD=%JAVA_HOME%\bin\java.exe"
if exist "%JAVACMD%" goto run

echo.
echo Error: JAVA_HOME is set to an invalid directory.
echo JAVA_HOME = "%JAVA_HOME%"
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.
echo.
goto error

:run
set "MAVEN_CMD_LINE_ARGS=%*"

"%JAVACMD%" %MAVEN_OPTS% -classpath "%MAVEN_WPR_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_WPR_PROJECTBASEDIR%" %MAVEN_WPR_CLASS% %MAVEN_CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_BATCH_PAUSE%" == "on" goto skipPause
:pause
pause
:skipPause

exit /b %ERROR_CODE%
