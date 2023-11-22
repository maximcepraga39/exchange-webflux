create table schimb_valutar (
    id serial primary key,
    valuta_id integer references dictionar_valute(id),
    curs_valutar_id integer references curs_valutar(id),
    suma_primita decimal(10, 2) not null,
    suma_eliberata decimal(10, 2) not null,
    utilizator varchar(255) not null ,
    data_schimb date not null
);