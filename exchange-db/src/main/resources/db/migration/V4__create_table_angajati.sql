create table angajati (
    id serial primary key,
    nume varchar(255) not null,
    prenume varchar(255) not null,
    functie varchar(255) not null,
    data_angajarii date not null
);