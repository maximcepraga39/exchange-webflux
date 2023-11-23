create table rapoarte_zilnice (
    id serial primary key,
    casa_de_schimb_id integer references casa_de_schimb(id) not null,
    valuta_id integer references dictionar_valute(id) not null,
    total_tranzactii integer not null,
    suma_primita decimal(10, 2) not null,
    suma_eliberata decimal(10, 2) not null,
    data_raport date not null,
    unique (casa_de_schimb_id, valuta_id, data_raport)
);