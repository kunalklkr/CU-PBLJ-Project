@echo off
echo ========================================
echo Starting MongoDB Service
echo ========================================
echo.

echo Checking if MongoDB service exists...
sc query MongoDB > nul 2>&1
if %errorlevel% neq 0 (
    echo MongoDB service not found.
    echo.
    echo Please install MongoDB from:
    echo https://www.mongodb.com/try/download/community
    echo.
    pause
    exit /b 1
)

echo Starting MongoDB service...
net start MongoDB
if %errorlevel% equ 0 (
    echo.
    echo MongoDB started successfully!
    echo Connection: mongodb://localhost:27017
) else (
    echo.
    echo MongoDB may already be running or requires administrator privileges.
    echo Try running this script as Administrator.
)
echo.
pause
