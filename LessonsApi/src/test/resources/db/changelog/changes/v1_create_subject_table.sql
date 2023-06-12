create table if not exists lessons_api.subject
(
    id bigint not null,
    name varchar(255),
    lecturer_id bigint not null
);

alter table lessons_api.subject owner to vkr123;

alter table lessons_api.subject
    add primary key (id);