--liquibase formatted sql

--changeset ypilipenko:fill-book-comments-stub-data
INSERT INTO otus_library.book_comments (book_id, email, text)
VALUES (1, 'example@gmail.com', 'Отличная книга!');
--rollback DELETE FROM otus_library.book_comments WHERE id = 1;
