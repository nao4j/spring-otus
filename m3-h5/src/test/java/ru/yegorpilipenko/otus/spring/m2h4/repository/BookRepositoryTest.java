package ru.yegorpilipenko.otus.spring.m2h4.repository;

import lombok.val;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m2h4.model.Author;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;
import ru.yegorpilipenko.otus.spring.m2h4.model.Genre;

import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest implements WithAssertions {

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void clean() {
        bookRepository.deleteAll();
    }

    @Nested
    class FindAllShortTest {

        @Test
        void shouldPassForExistsBooks() {
            val book1Id = bookRepository.save(Book.builder()
                    .name("b1")
                    .authors(List.of(new Author("f1", "l1"), new Author("f2", "l2")))
                    .genres(List.of(new Genre("n1")))
                    .comments(List.of(new Comment("e1", "t1")))
                    .build()
            ).getId();
            val book2Id = bookRepository.save(Book.builder()
                    .name("b2")
                    .authors(List.of(new Author("f3", "l3")))
                    .genres(List.of(new Genre("n2")))
                    .build()
            ).getId();
            assertThat(bookRepository.findAllShort()).containsExactlyInAnyOrder(
                    new ShortBook(book1Id, "b1"),
                    new ShortBook(book2Id, "b2")
            );
        }

        @Test
        void shouldPassForNotExistsBooks() {
            assertThat(bookRepository.findAllShort()).isEmpty();
        }

    }

    @Nested
    class RemoveByIdTest {

        @Test
        void shouldPassForExistsBook() {
            val savedBook = bookRepository.save(Book.builder().name("Book for test").build());
            assertThat(bookRepository.removeById(savedBook.getId())).isEqualTo(1);
        }

        @Test
        void shouldPassForNotExistsBook() {
            assertThat(bookRepository.removeById("1")).isEqualTo(0);
        }

    }

    @Nested
    class FindAllAuthorsTest {

        @Test
        void shouldPassForExistsAuthors() {
            val author1 = new Author("f1", "l1");
            val author2 = new Author("f2", "l2");
            val author3 = new Author("f3", "l3");
            bookRepository.save(Book.builder().name("n1").authors(List.of(author1, author2)).build());
            bookRepository.save(Book.builder().name("n2").authors(List.of(author2, author3)).build());
            assertThat(bookRepository.findAllAuthors()).containsExactlyInAnyOrder(author1, author2, author3);
        }

        @Test
        void shouldPassForNotExistsAuthors() {
            bookRepository.save(Book.builder().name("n1").build());
            bookRepository.save(Book.builder().name("n2").build());
            assertThat(bookRepository.findAllAuthors()).isEmpty();
        }

        @Test
        void shouldPassForNotExistsBooks() {
            assertThat(bookRepository.findAllAuthors()).isEmpty();
        }

    }

    @Nested
    class FindAllGenresTest {

        @Test
        void shouldPassForExistsGenres() {
            val genre1 = new Genre("g1");
            val genre2 = new Genre("g2");
            val genre3 = new Genre("g3");
            bookRepository.save(Book.builder().name("n1").genres(List.of(genre1, genre2)).build());
            bookRepository.save(Book.builder().name("n2").genres(List.of(genre2, genre3)).build());
            assertThat(bookRepository.findAllGenres()).containsExactlyInAnyOrder(genre1, genre2, genre3);
        }

        @Test
        void shouldPassForNotExistsGenres() {
            bookRepository.save(Book.builder().name("n1").build());
            bookRepository.save(Book.builder().name("n2").build());
            assertThat(bookRepository.findAllGenres()).isEmpty();
        }

        @Test
        void shouldPassForNotExistsBooks() {
            assertThat(bookRepository.findAllGenres()).isEmpty();
        }

    }

}
