alter table numerar
add column casa_de_schimb_id integer references casa_de_schimb(id) not null default 1;

alter table numerar
add constraint unique_valuta_id_casa_de_schimb_id unique(valuta_id, casa_de_schimb_id);