create table if not exists pessoas(

    id SERIAL,
    name varchar(500) not null,
    gender char(1) not null,
    city varchar(50) not null,
    email varchar(50) not null,
    pais_id integer not null,

    constraint pk_pessoa primary key (id),
    constraint fk_pessoa_pais foreign key(pais_id) references paises(id)

);