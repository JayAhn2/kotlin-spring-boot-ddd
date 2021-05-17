CREATE TABLE IF NOT EXISTS books
(
    id       bigint(20)   NOT NULL,
    title     VARCHAR(100) NOT NULL,
    isbn     varchar(100) not null,
    pages    INTEGER      not null,
    author_id bigint(20)   not null,
    PRIMARY KEY (id)
);
