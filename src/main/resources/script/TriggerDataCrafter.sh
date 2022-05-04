echo "Data generation started"

if test "$#" -ne 1; then
    echo "Illegal number of parameters | Kindly pass valid schema file name "
    exit -1
fi
base_location=`pwd`
file_name=env.properties
config_path=$base_location/conf/env
common_config_file=$config_path/${file_name}

#loading config file
. ${common_config_file}

java -jar $executablejar $applicationprop $1
echo "Data generation finished"