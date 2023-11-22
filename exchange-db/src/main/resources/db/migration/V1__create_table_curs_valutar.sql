create table curs_valutar (
    id serial primary key,
    valuta_id integer references dictionar_valute(id),
    curs decimal(10, 4) not null,
    data_curs date not null,
    unique (valuta_id, data_curs)
);