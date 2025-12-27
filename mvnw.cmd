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
@REM M2_HOME - location of maven2's installed home dir
@REM MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM MAVEN_BATCH_PAUSE - set to 'on' to wait for a keystroke before ending
@REM MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM     set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM ----------------------------------------------------------------------------

@echo off
@setlocal

set ERROR_CODE=0

@REM To isolate internal variables from possible setting of calling script
set wrapperJarPath=.mvn\wrapper\maven-wrapper.jar
set wrapperPropertiesPath=.mvn\wrapper\maven-wrapper.properties
set mavenWrapperMainClass=org.apache.maven.wrapper.MavenWrapperMain

@REM Determine the project base directory
set "BASE_DIR=%~dp0"
set "BASE_DIR=%BASE_DIR:~0,-1%"

@REM Find the JAVA_EXE to use
if not "%JAVA_HOME%"=="" (
  set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
) else (
  set "JAVA_EXE=java.exe"
)

@REM Check if wrapper jar exists
if exist "%BASE_DIR%\%wrapperJarPath%" (
  goto :run
)

@REM Download wrapper jar if not exists
echo Downloading Maven Wrapper...
set "DOWNLOAD_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
powershell -Command "& { [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webclient = New-Object System.Net.WebClient; if (-not (Test-Path '%BASE_DIR%\.mvn\wrapper')) { New-Item -Path '%BASE_DIR%\.mvn\wrapper' -ItemType Directory | Out-Null }; $webclient.DownloadFile('%DOWNLOAD_URL%', '%BASE_DIR%\%wrapperJarPath%') }"

:run
"%JAVA_EXE%" %MAVEN_OPTS% -classpath "%BASE_DIR%\%wrapperJarPath%" %mavenWrapperMainClass% %*

if ERRORLEVEL 1 set ERROR_CODE=1

@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_BATCH_PAUSE%" == "on" goto end
pause

:end
exit /B %ERROR_CODE%
