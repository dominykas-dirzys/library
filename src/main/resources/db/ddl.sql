create database library;

create table user
(
    id         bigint unsigned auto_increment primary key,
    name       varchar(250)    not null
);

create table book
(
    id         bigint unsigned auto_increment primary key,
    name       varchar(250)    not null,
    author     varchar(250)    not null,
    category   varchar(250)    not null,
    language   varchar(250)    not null,
    publication_date    date    not null,
    isbn       varchar(250)    not null,
    user_id    bigint unsigned,
    constraint fk_book_user foreign key (user_id) references user (id)
);
