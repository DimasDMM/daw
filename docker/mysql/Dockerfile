FROM mysql/mysql-server:5.6

ENV MYSQL_ROOT_HOST=%
ENV MYSQL_DATABASE=daw
ENV MYSQL_ROOT_PASSWORD=pass

COPY ./database.sql /docker-entrypoint-initdb.d/database.sql

EXPOSE 3306