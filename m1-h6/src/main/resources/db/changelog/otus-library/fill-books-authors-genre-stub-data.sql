--liquibase formatted sql

--changeset ypilipenko:fill-books-stub-data
INSERT INTO otus_library.books (name)
VALUES
 ('Шаблоны корпоративных приложений'),
 ('Архитектура корпоративных программных приложений'),
 ('Рефакторинг. Улучшение существующего кода'),
 ('Основы. Краткое руководство по унифицированному языку моделирования'),
 ('Трудно быть богом');
--rollback DELETE FROM otus_library.books WHERE id BETWEEN 1 AND 5;

--changeset ypilipenko:fill-authors-stub-data
INSERT INTO otus_library.authors(first_name, last_name)
VALUES
 ('Мартин', 'Фаулер'),
 ('Кендалл', 'Скотт'),
 ('Аркадий', 'Стругацкий'),
 ('Борис', 'Стругацкий');
--rollback DELETE FROM otus_library.authors WHERE id BETWEEN 1 AND 4;

--changeset ypilipenko:fill-genres-stub-data
INSERT INTO otus_library.genres(name)
VALUES
 ('Научно-техническая литература'),
 ('Научная фантастика'),
 ('Драма');
--rollback DELETE FROM otus_library.genres WHERE id BETWEEN 1 AND 3;

--changeset ypilipenko:fill-books-authors-stub-data
INSERT INTO otus_library.books_authors(book_id, author_id)
VALUES
 (1, 1),
 (2, 1),
 (3, 1),
 (4, 1),
 (4, 3),
 (5, 3),
 (5, 4);
--rollback DELETE FROM otus_library.books_authors WHERE book_id BETWEEN 1 AND 5;

--changeset ypilipenko:fill-books-genres-stub-data
INSERT INTO otus_library.books_genres(book_id, genre_id)
VALUES
 (1, 1),
 (2, 1),
 (3, 1),
 (4, 1),
 (5, 2),
 (5, 3);
--rollback DELETE FROM otus_library.books_genres WHERE book_id BETWEEN 1 AND 5;
