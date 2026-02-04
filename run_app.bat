@echo off
echo Starting CNG Connect Backend...
echo Checking for Maven...

mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Maven is not installed or not in your PATH.
    echo Please install Maven from https://maven.apache.org/download.cgi
    echo.
    echo Press any key to exit...
    pause >nul
    exit /b 1
)

echo Maven found. Starting application...
call mvn spring-boot:run

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Application failed to start.
    echo PLEASE CHECK THE ERROR MESSAGES ABOVE.
    pause
)
