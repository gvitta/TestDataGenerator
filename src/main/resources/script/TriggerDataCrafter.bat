@echo off
echo "Data generation started"

SET base_location=%cd%
SET file_name=env_win.properties
SET config_path=%base_location%\conf\env
SET common_config_file=%config_path%\%file_name%

::loading config file
FOR /F "tokens=1,2 delims==" %%G IN (%common_config_file%) DO (set %%G=%%H)

java -jar %executablejar% %applicationprop% %1
echo "Data generation finished"
