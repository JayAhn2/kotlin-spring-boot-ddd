CREATE TABLE IF NOT EXISTS book
(
    id    bigint(20)   NOT NULL,
    title VARCHAR(100) NOT NULL,
    isbn  varchar(100) NOT NULL,
    pages INTEGER      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book_author
(
    book_id   bigint(20) NOT NULL,
    author_id bigint(20) NOT NULL,
    CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE IF NOT EXISTS author
(
    id         bigint(20)    NOT NULL,
    first_name VARCHAR(100)  NOT NULL,
    last_name  varchar(100)  NOT NULL,
    age        INTEGER       NOT NULL,
    biography  VARCHAR(3000) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS author_book
(
    author_id bigint(20) NOT NULL,
    book_id   bigint(20) NOT NULL,
    CONSTRAINT author_id_fk FOREIGN KEY (author_id) REFERENCES author (id)
);
