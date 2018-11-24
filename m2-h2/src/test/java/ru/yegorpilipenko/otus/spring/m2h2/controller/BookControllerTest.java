package ru.yegorpilipenko.otus.spring.m2h2.controller;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Comment;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;
import ru.yegorpilipenko.otus.spring.m2h2.service.BookService;

import java.util.Optional;
import java.util.Scanner;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookControllerTest implements WithAssertions {

    @Mock
    BookService bookService;
    Scanner scanner;
    BookController controller;

    @BeforeEach
    void setup() {
        scanner = new Scanner("af1 al1, af2 al2\ng1, g2");
        controller = new BookController(scanner, bookService);
    }

    @Nested
    class GetAllTest {

        @Test
        void shouldPassForMoreBooks() {
            doReturn(asList(new Book("1", "b1"), new Book("2", "b2"))).when(bookService).getAll();
            assertThat(controller.getAll()).containsExactlyInAnyOrder("1: b1", "2: b2");
        }

        @Test
        void shouldPassWithoutBooks() {
            doReturn(emptyList()).when(bookService).getAll();
            assertThat(controller.getAll()).isEmpty();
        }

    }

    @Nested
    class GetByIdTest {

        @Test
        void shouldPassForExistsBook() {
            final String id = "1";
            final Author author = new Author("af", "al");
            final Genre genre = new Genre("g");
            final Comment comment = new Comment("test@email.com", "text");
            doReturn(Optional.of(new Book(
                    id,
                    "b",
                    singletonList(author),
                    singletonList(genre),
                    singletonList(comment)
            ))).when(bookService).getById(id);
            assertThat(controller.getById(id))
                    .isEqualTo("1: b\nАвторы:\naf al\nЖанры:\ng\nКомментарии:\ntest@email.com\ntext");
        }

        @Test
        void shouldPassForExistsShortBook() {
            final String id = "1";
            doReturn(Optional.of(new Book(id, "b", emptyList(), emptyList(), emptyList())))
                    .when(bookService).getById(id);
            assertThat(controller.getById(id)).isEqualTo("1: b");
        }

        @Test
        void shouldPassForNotExistsBook() {
            final String id = "1";
            doReturn(Optional.empty()).when(bookService).getById(id);
            assertThat(controller.getById(id)).isEqualTo("Not found");
        }

    }

    @Nested
    class SaveTest {

        @Test
        void shouldPassForSaveFullBook() {
            doReturn(new Book(
                    "1",
                    "b",
                    asList(new Author("af1", "al1"), new Author("af2", "al2")),
                    asList(new Genre("g1"), new Genre("g2")),
                    emptyList()
            )).when(bookService).save(any(), any(), any());
            assertThat(controller.save("b")).isEqualTo("1: b\nАвторы:\naf1 al1\naf2 al2\nЖанры:\ng1\ng2");
            verify(bookService).save(
                    "b",
                    asList(new Author("af1", "al1"), new Author("af2", "al2")),
                    asList(new Genre("g1"), new Genre("g2"))
            );
        }

    }

    @Nested
    class RemoveTest {

        @Test
        void shouldPassForExistsBook() {
            final String id = "1";
            doReturn(true).when(bookService).remove(id);
            assertThat(controller.remove(id)).isEqualTo("Removed");
        }

        @Test
        void shouldPassForNotExistsBook() {
            final String id = "1";
            doReturn(false).when(bookService).remove(id);
            assertThat(controller.remove(id)).isEqualTo("Not found");
        }

    }

}
