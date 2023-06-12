create table if not exists token
(
    token varchar(255) not null
);

alter table token owner to vkr123;

alter table token
    add primary key (token);