CREATE TABLE IF NOT EXISTS book
(
    id    bigint(20)   NOT NULL,
    title VARCHAR(100) NOT NULL,
    isbn  varchar(100) not null,
    pages INTEGER      not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book_author
(
    book_id   bigint(20) NOT NULL,
    author_id bigint(20) NOT NULL,
    constraint book_id_fk foreign key (book_id) references book (id)
);

create table if not exists author
(
    id         bigint(20)   NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name  varchar(100) not null,
    age        INTEGER      not null,
    biography  bigint(20)   not null,
    PRIMARY KEY (id)
);

create table if not exists author_book
(
    author_id bigint(20) NOT NULL,
    book_id   bigint(20) NOT NULL,
    constraint author_id_fk foreign key (author_id) references author (id)
);
