create table students
(
    id                 bigserial primary key not null,
    first_name         varchar(255)          not null,
    last_name          varchar(255),
    father_name        varchar(255),
    file_id            varchar(255),
    age                int                   not null,
    record_book_number varchar(255) unique   not null,
    created_at         timestamp without time zone,
    updated_at         timestamp without time zone
);

create table files (
    id  text,
    name text,
    size bigint,
    content_type varchar(255)
);