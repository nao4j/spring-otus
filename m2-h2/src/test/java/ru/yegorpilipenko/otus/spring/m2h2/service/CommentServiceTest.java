package ru.yegorpilipenko.otus.spring.m2h2.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Comment;
import ru.yegorpilipenko.otus.spring.m2h2.repository.BookRepository;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest implements WithAssertions {

    @Mock
    BookRepository bookRepository;
    CommentService service;

    @BeforeEach
    void setup() {
        service = new CommentServiceImpl(bookRepository);
    }

    @Nested
    class AddTest {

        @Test
        void shouldPassForNormalBook() {
            final String bookId = "1";
            final Comment comment = new Comment("test@gmail.com", "Comment text");
            final Book book = new Book(bookId, "Test Book", emptyList(), emptyList(), new ArrayList<>());
            doReturn(Optional.of(book)).when(bookRepository).findById(bookId);
            assertThat(service.add(bookId, comment)).isEqualTo(comment);
            verify(bookRepository).findById(bookId);
            verify(bookRepository).save(any(Book.class));
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldFailIfBookNotFound() {
            final String bookId = "1";
            final Comment comment = new Comment("test@gmail.com", "Comment text");
            doReturn(Optional.empty()).when(bookRepository).findById(bookId);
            assertThatThrownBy(() -> service.add(bookId, comment)).isInstanceOf(IllegalArgumentException.class);
            verify(bookRepository).findById(bookId);
            verifyNoMoreInteractions(bookRepository);
        }

    }

    @Nested
    class RemoveTest {

        @Test
        void shouldPassForNormalComment() {
            final String bookId = "1";
            final Comment comment = new Comment("test@gmail.com", "Comment text");
            final Book book = new Book(bookId, "Test Book", emptyList(), emptyList(), asList(comment));
            doReturn(Optional.of(book)).when(bookRepository).findById(bookId);
            assertThat(service.remove(bookId, comment)).isTrue();
            verify(bookRepository).findById(bookId);
            verify(bookRepository).save(any(Book.class));
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldPassForNotExistsComment() {
            final String bookId = "1";
            final Comment comment = new Comment("test@gmail.com", "Comment text");
            final Book book = new Book(bookId, "Test Book", emptyList(), emptyList(), new ArrayList<>());
            doReturn(Optional.of(book)).when(bookRepository).findById(bookId);
            assertThat(service.remove(bookId, comment)).isFalse();
            verify(bookRepository).findById(bookId);
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldFailIfBookNotFound() {
            final String bookId = "1";
            final Comment comment = new Comment("test@gmail.com", "Comment text");
            doReturn(Optional.empty()).when(bookRepository).findById(bookId);
            assertThatThrownBy(() -> service.remove(bookId, comment)).isInstanceOf(IllegalArgumentException.class);
            verify(bookRepository).findById(bookId);
            verifyNoMoreInteractions(bookRepository);
        }

    }

}
