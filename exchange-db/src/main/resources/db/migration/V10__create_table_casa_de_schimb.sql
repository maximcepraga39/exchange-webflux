create table casa_de_schimb (
    id serial primary key,
    nume varchar(255) not null,
    adresa varchar(255) not null,
    telefon varchar(255) not null,
    email varchar(255) not null,
    data_infiintare date not null
);