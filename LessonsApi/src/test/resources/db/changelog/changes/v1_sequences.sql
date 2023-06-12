create sequence lesson_seq;

alter sequence lesson_seq owner to vkr123;

create sequence person_seq;

alter sequence person_seq owner to vkr123;

create sequence subject_seq;

alter sequence subject_seq owner to vkr123;

-- alter table lessons_api.lesson_students
--     add constraint fk6pr229u8931m9lf54ti2r40sm
--         foreign key (student_id) references lessons_api.person;
--
-- alter table lessons_api.lesson_students
--     add constraint fkavvumej5p27ywc0qf5i0q6dui
--         foreign key (lesson_id) references lessons_api.lesson;
--
-- alter table lessons_api.lesson
--     add constraint fk7ydr23s8y9j6lip5qrngoymx4
--         foreign key (subject_id) references lessons_api.subject;
--
-- alter table lessons_api.lesson
--     add constraint fkskh4v0cacydp75biopkr9wrmm
--         foreign key (teacher_id) references lessons_api.person;
--
-- alter table lessons_api.lesson_students
--     add constraint fk6pr229u8931m9lf54ti2r40sm
--         foreign key (student_id) references lessons_api.person;
--
-- alter table lessons_api.lesson_students
--     add constraint fkavvumej5p27ywc0qf5i0q6dui
--         foreign key (lesson_id) references lessons_api.lesson;
--
-- alter table lessons_api.subject
--     add constraint fklcga52aasfrg2u5s1tvvw5rg3
--         foreign key (lecturer_id) references lessons_api.person;