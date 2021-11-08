CREATE SEQUENCE camb_id_seq;
CREATE TABLE cambio (
  id int not null default nextval('camb_id_seq'),
  from_currency CHAR(3) NOT NULL,
  to_currency CHAR(3) NOT NULL,
  conversion_factor decimal(65,2) NOT NULL,

  primary key (id)
);
ALTER SEQUENCE camb_id_seq OWNED BY cambio.id;