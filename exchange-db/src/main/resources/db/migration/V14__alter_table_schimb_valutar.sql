alter table schimb_valutar
add column casa_de_schimb_id integer references casa_de_schimb(id) not null default 1;