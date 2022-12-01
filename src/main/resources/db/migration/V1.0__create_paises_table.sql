create table if not exists paises (

    id SERIAL not null,
    name varchar(50) not null,

    constraint pk_pais primary key(id)

);