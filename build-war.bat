@echo off
REM Script pour builder le projet complet (Frontend + Backend) en un WAR

echo ========================================
echo Sushi Talence - Build Complet
echo ========================================

echo.
echo [1/3] Building Frontend Angular...
cd front
call npm run build -- --configuration production
if errorlevel 1 (
    echo ❌ Erreur lors du build Angular!
    pause
    exit /b 1
)
cd ..

echo.
echo [2/3] Copie des fichiers statiques dans le Backend...
set SOURCE=%cd%\front\dist\sushi\browser
set DEST=%cd%\back\src\main\resources\static

if not exist "%DEST%" (
    mkdir "%DEST%"
)

REM Vider le dossier static existant
for /d %%i in ("%DEST%\*") do rd /s /q "%%i"
del /q "%DEST%\*" 2>nul

REM Copier les nouveaux fichiers
xcopy "%SOURCE%" "%DEST%" /E /Y

echo.
echo [3/3] Building WAR avec Maven...
cd back
call .\mvnw.cmd clean package -DskipTests
if errorlevel 1 (
    echo ❌ Erreur lors du build Maven!
    cd ..
    pause
    exit /b 1
)
cd ..

echo.
echo ========================================
echo ✅ BUILD RÉUSSI!
echo ========================================
echo.
echo 📦 Fichier WAR créé: 
echo    %cd%\back\target\ROOT.war
echo.
echo 🚀 Prêt à déployer sur Tomcat!
echo    Voir DEPLOYMENT.md pour les instructions
echo.
pause
