CREATE SEQUENCE otus_library.authors_id_sq
  MINVALUE 1
  INCREMENT BY 1
  NO CYCLE;

CREATE TABLE otus_library.authors (
  id         BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('otus_library.authors_id_sq'),
  first_name VARCHAR(128) NOT NULL,
  last_name  VARCHAR(128) NOT NULL
);
