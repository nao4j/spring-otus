CREATE TABLE otus_library.books_authors (
  book_id   BIGINT NOT NULL REFERENCES otus_library.books(id) ON DELETE CASCADE,
  author_id BIGINT NOT NULL REFERENCES otus_library.authors(id) ON DELETE CASCADE
);
