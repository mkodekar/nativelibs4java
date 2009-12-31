@echo off
setlocal enabledelayedexpansion

for /D %%F in (dyncall32 dyncall64 dyncallback32 dyncallback64 dynload jdyncall32 jdyncall64) do (
	for %%S in (x64 Debug Release obj build) do (
		if exist "%%F\%%S" (
			echo Deleting "%%F\%%S"
			rmdir /S /Q "%%F\%%S"
		)
	)
)

del /Q x64\Release\*.*
del /Q x64\Debug\*.*
del /Q Release\*.*
del /Q Debug\*.*

del /Q *.ncb

if not "%1" == "nopause" pause
