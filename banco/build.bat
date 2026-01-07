@echo off
echo ============================================
echo BancoBarber - Configurar JAVA_HOME y Compilar
echo ============================================
echo.

REM Configurar JAVA_HOME temporalmente
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

echo JAVA_HOME configurado: %JAVA_HOME%
echo.

REM Verificar Java
echo Verificando Java...
java -version
echo.

REM Verificar Maven
echo Verificando Maven...
mvn -version
echo.

REM Limpiar y compilar
echo ============================================
echo Limpiando proyecto...
echo ============================================
call mvn clean

echo.
echo ============================================
echo Compilando proyecto...
echo ============================================
call mvn compile

echo.
echo ============================================
echo Empaquetando proyecto...
echo ============================================
call mvn package -DskipTests

echo.
echo ============================================
echo Compilación completada!
echo ============================================
echo.
echo Para ejecutar la aplicación, usa:
echo   mvn spring-boot:run
echo.
echo O ejecuta:
echo   run_application.bat
echo.
pause
