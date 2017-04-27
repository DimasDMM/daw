#!/bin/bash

echo "Waiting for MySQL";

while ! mysql -u root -p'pass' -h mysql_server -P 33060 -e 'SELECT * FROM daw.articles';
do
  echo "Waiting..."
  sleep 1
done

echo "Done"
exit 0
