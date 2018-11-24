CREATE TABLE otus_library.books_genres (
  book_id  BIGINT NOT NULL REFERENCES otus_library.books(id) ON DELETE CASCADE,
  genre_id BIGINT NOT NULL REFERENCES otus_library.genres(id) ON DELETE CASCADE
);
