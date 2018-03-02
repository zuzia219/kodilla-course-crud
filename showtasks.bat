call runcrud
if "%ERRORLEVEL%" == "0" goto gettasks
echo.
echo runcrud.bat has errors - breaking work
goto fail

:gettasks
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open Chrome and show tasks - breaking work

:fail
echo.
echo There were errors

:end
echo.
echo Showtask script's work is finished.
