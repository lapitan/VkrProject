create table if not exists lessons_api.person
(
    id bigint not null,
    confirmed boolean,
    firstname varchar(255),
    groupp varchar(255),
    lastname varchar(255),
    password varchar(255),
    role varchar(255),
    username varchar(255)
);

alter table lessons_api.person owner to vkr123;

alter table lessons_api.person
    add primary key (id);
