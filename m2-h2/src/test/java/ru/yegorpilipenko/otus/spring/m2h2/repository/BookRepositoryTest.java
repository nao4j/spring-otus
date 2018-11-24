package ru.yegorpilipenko.otus.spring.m2h2.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest implements WithAssertions {

    @Autowired
    BookRepository bookRepository;

    @AfterEach
    void clean() {
        bookRepository.deleteAll();
    }

    @Nested
    class FindAllAuthorsTest {

        @Test
        void shouldPassForNormalCollection() {
            bookRepository.saveAll(asList(
                    new Book(
                            "1",
                            "Книга 1",
                            singletonList(new Author("Вася", "Пупкин")),
                            singletonList(new Genre("Фунтази")),
                            emptyList()
                    ),
                    new Book(
                            "2",
                            "Книга 2",
                            singletonList(new Author("Ваня", "Иванов")),
                            singletonList(new Genre("Фунтази")),
                            emptyList()
                    )
            ));
            assertThat(bookRepository.findAllAuthors()).containsExactlyInAnyOrder(
                    new Author("Вася", "Пупкин"),
                    new Author("Ваня", "Иванов")
            );
        }

        @Test
        void shouldPassForEmptyCollection() {
            assertThat(bookRepository.findAllAuthors()).isEmpty();
        }

    }

    @Nested
    class FindAllGenresTest {

        @Test
        void shouldPassForNormalCollection() {
            bookRepository.saveAll(asList(
                    new Book(
                            "1",
                            "Книга 1",
                            singletonList(new Author("Вася", "Пупкин")),
                            singletonList(new Genre("Фунтази")),
                            emptyList()
                    ),
                    new Book(
                            "2",
                            "Книга 2",
                            singletonList(new Author("Ваня", "Иванов")),
                            singletonList(new Genre("Фунтази")),
                            emptyList()
                    )
            ));
            assertThat(bookRepository.findAllGenres()).containsExactlyInAnyOrder(new Genre("Фунтази"));
        }

        @Test
        void shouldPassForEmptyCollection() {
            assertThat(bookRepository.findAllGenres()).isEmpty();
        }

    }

    @Nested
    class RemoveByIdTest {

        @Test
        void shouldPassForDeleteExistsBook() {
            bookRepository.save(
                    new Book(
                            "1",
                            "Книга 1",
                            singletonList(new Author("Вася", "Пупкин")),
                            singletonList(new Genre("Фунтази")),
                            emptyList()
                    )
            );
            assertThat(bookRepository.removeById("1")).isEqualTo(1);
        }

        @Test
        void shouldPassForDeleteNotExistsBook() {
            assertThat(bookRepository.removeById("1")).isEqualTo(0);
        }

    }

}
