create table numerar (
    id serial primary key,
    valuta_id integer references dictionar_valute(id),
    utilizator varchar(255) not null,
    suma decimal(10, 2) not null,
    data date not null

);