create table if not exists lessons_api.lesson
(
    id bigint not null,
    groupp varchar(255),
    subject_id bigint not null,
    teacher_id bigint not null
);

alter table lessons_api.lesson owner to vkr123;

alter table lessons_api.lesson
    add primary key (id);