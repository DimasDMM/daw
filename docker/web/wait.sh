#!/bin/bash

echo "Waiting for MySQL connection..."
sleep 15

echo "Executing Web Server..."
exec java -jar /home/themadridtimes.jar
