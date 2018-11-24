CREATE SEQUENCE otus_library.books_id_sq
  MINVALUE 1
  INCREMENT BY 1
  NO CYCLE;

CREATE TABLE otus_library.books (
  id   BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('otus_library.books_id_sq'),
  name VARCHAR(512) NOT NULL
);
