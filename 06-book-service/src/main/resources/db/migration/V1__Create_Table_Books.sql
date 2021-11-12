CREATE SEQUENCE book_id_seq;
CREATE TABLE book (
    id int not null default nextval('book_id_seq'),
    author varchar(100),
    launch_date timestamp NOT NULL,
    price decimal(65,2) NOT NULL,
    title varchar(100)
);

ALTER SEQUENCE book_id_seq OWNED BY book.id;
