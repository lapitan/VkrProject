create table if not exists lessons_api.lesson_students
(
    lesson_id bigint not null,
    student_id bigint not null
);

alter table lessons_api.lesson_students owner to vkr123;