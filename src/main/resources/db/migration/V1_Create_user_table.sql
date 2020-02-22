-- auto-generated definition
create table user
(
    id           int auto_increment
        primary key,
    name         varchar(50)  null,
    account_id   varchar(100) null,
    token        varchar(100) null,
    gmt_create   bigint       null,
    gmt_modified bigint       null,
    bio          varchar(256) null
);
