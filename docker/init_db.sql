create user exchange_user with password 'exchange123';

create database exchange;
grant all privileges on database exchange to exchange_user;