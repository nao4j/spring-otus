CREATE SEQUENCE otus_library.genres_id_sq
  MINVALUE 1
  INCREMENT BY 1
  NO CYCLE;

CREATE TABLE otus_library.genres (
  id   BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('otus_library.genres_id_sq'),
  name VARCHAR(128) NOT NULL
);
