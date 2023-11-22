create table dictionar_valute (
    id serial primary key,
    cod_valuta varchar(3) unique not null,
    nume_valuta varchar(50) not null
);