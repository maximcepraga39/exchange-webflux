FROM postgres:14-alpine
COPY init_db.sql /docker-entrypoint-initdb.d/