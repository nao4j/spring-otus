INSERT INTO otus_library.books (id, name)
VALUES (1, 'Шаблоны корпоративных приложений'),
       (2, 'Архитектура корпоративных программных приложений'),
       (3, 'Рефакторинг. Улучшение существующего кода'),
       (4, 'Основы. Краткое руководство по унифицированному языку моделирования'),
       (5, 'Трудно быть богом');

INSERT INTO otus_library.authors(id, first_name, last_name)
VALUES (1, 'Мартин', 'Фаулер'),
       (2, 'Кендалл', 'Скотт'),
       (3, 'Аркадий', 'Стругацкий'),
       (4, 'Борис', 'Стругацкий');

INSERT INTO otus_library.genres(id, name)
VALUES (1, 'Научно-техническая литература'),
       (2, 'Научная фантастика'),
       (3, 'Драма');

INSERT INTO otus_library.books_authors(book_id, author_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (4, 2),
       (5, 3),
       (5, 4);

INSERT INTO otus_library.books_genres(book_id, genre_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (5, 3);
