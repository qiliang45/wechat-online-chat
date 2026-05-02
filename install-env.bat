@echo off
echo ===========================================
echo    微信聊天项目 - 开发环境安装脚本
echo ===========================================
echo.

REM 检查 Node.js
echo [1/4] 检查 Node.js...
where node >nul 2>&1
if %errorLevel% equ 0 (
    for /f "delims=" %%i in ('node -v') do set NODE_VERSION=%%i
    echo     Node.js 已安装: !NODE_VERSION!
) else (
    echo     [错误] Node.js 未安装
    echo     请从 https://nodejs.org 下载安装
    goto :manual_install
)
echo.

REM 配置 npm 执行策略
echo [2/4] 配置 npm 执行策略...
powershell -Command "Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser -Force" >nul 2>&1
if %errorLevel% equ 0 (
    echo     npm 执行策略已配置
) else (
    echo     [警告] npm 执行策略配置失败，请手动运行：
    echo     powershell -Command "Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser"
)
echo.

REM 检查 Java
echo [3/4] 检查 Java...
where java >nul 2>&1
if %errorLevel% equ 0 (
    echo     Java 已安装
    java -version 2>&1 | findstr /i "version"
) else (
    echo     [错误] Java 未安装
    echo     请从 https://adoptium.net 下载 JDK 21+
    goto :manual_install
)
echo.

REM 检查 Maven
echo [4/4] 检查 Maven...
where mvn >nul 2>&1
if %errorLevel% equ 0 (
    echo     Maven 已安装
    mvn -version 2>&1 | findstr /i "apache"
) else (
    echo     [错误] Maven 未安装
    echo     请从 https://maven.apache.org/download.cgi 下载
    goto :manual_install
)
echo.

echo ===========================================
echo    环境检查完成！
echo ===========================================
echo.
echo 接下来请确保 MySQL 已安装并运行
echo.
echo 快速安装 MySQL:
echo   winget install Oracle.MySQL
echo.
echo 或从 https://dev.mysql.com/downloads/mysql/ 下载
echo.
pause
exit /b 0

:manual_install
echo.
echo ===========================================
echo    缺少必要环境，请先安装缺失组件
echo ===========================================
pause
exit /b 1