#!/bin/sh

echo "Waiting for MySQL connection"

while ! mysql -u root -p pass -h mysql_server -e 'SELECT * FROM daw.articles';
do
    sleep 1
    echo "Waiting..."
done

echo "Ok"
