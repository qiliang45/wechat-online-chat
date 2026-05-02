@echo off
chcp 65001 >nul
echo ===========================================
echo    ??? Maven
echo ===========================================
echo.

set "MAVEN_URL=https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip"
set "MAVEN_ZIP=apache-maven-3.9.9-bin.zip"
set "MAVEN_DIR=C:\Program Files\Apache\maven"

echo 1. ??????...
mkdir "C:\Program Files\Apache" >nul 2>&1

echo 2. ???? Maven...
powershell -Command "(New-Object System.Net.WebClient).DownloadFile('%MAVEN_URL%', '%MAVEN_ZIP%')"

echo 3. ??? Maven...
powershell -Command "Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath 'C:\Program Files\Apache'"

echo 4. ????????...
rename "C:\Program Files\Apache\apache-maven-3.9.9" maven

echo 5. ????...
del "%MAVEN_ZIP%"

echo.
echo Maven ???????
echo ¡¤??: %MAVEN_DIR%
echo.
pause