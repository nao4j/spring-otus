package ru.yegorpilipenko.otus.spring.m4h2.service;

import lombok.val;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yegorpilipenko.otus.spring.m4h2.model.Book;
import ru.yegorpilipenko.otus.spring.m4h2.model.Comment;
import ru.yegorpilipenko.otus.spring.m4h2.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class BookServiceTest implements WithAssertions {

    @Mock
    BookRepository bookRepository;
    BookService bookService;

    @BeforeEach
    void setup() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Nested
    class RemoveByIdTest {

        @Test
        void shouldPassIfExists() {
            val id = "1";
            doReturn(1).when(bookRepository).removeById(id);
            assertThat(bookService.removeById(id)).isTrue();
            verify(bookRepository).removeById(id);
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldPassIfNotExists() {
            val id = "1";
            doReturn(0).when(bookRepository).removeById(id);
            assertThat(bookService.removeById(id)).isFalse();
            verify(bookRepository).removeById(id);
            verifyNoMoreInteractions(bookRepository);
        }

    }

    @Nested
    class AddCommentTest {

        @Test
        void shouldPassForNormalBook() {
            val bookId = "1";
            val comment = new Comment("e2", "t2");
            val book = Book.builder()
                    .id(bookId)
                    .name("n")
                    .comments(List.of(new Comment("e1", "t1")))
                    .build();
            val expected = Book.builder()
                    .id(bookId)
                    .name("n")
                    .comments(List.of(new Comment("e1", "t1"), new Comment("e2", "t2")))
                    .build();
            doReturn(Optional.of(book)).when(bookRepository).findById(bookId);
            doReturn(expected).when(bookRepository).save(expected);
            assertThat(bookService.addComment(bookId, comment)).isEqualTo(expected);
            verify(bookRepository).findById(bookId);
            verify(bookRepository).save(expected);
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldFailIfBookNotExists() {
            val bookId = "1";
            doReturn(Optional.empty()).when(bookRepository).findById(bookId);
            assertThatThrownBy(() -> bookService.addComment(bookId, new Comment("e", "t")))
                    .isInstanceOf(IllegalArgumentException.class);
            verify(bookRepository).findById(bookId);
            verifyNoMoreInteractions(bookRepository);
        }

    }

    @Nested
    class RemoveCommentTest {

        @Test
        void shouldPassForExistsComment() {
            val bookId = "1";
            val comment = new Comment("e", "t");
            val book = Book.builder()
                    .id(bookId)
                    .name("n")
                    .comments(List.of(comment))
                    .build();
            val expected = Book.builder()
                    .id(bookId)
                    .name("n")
                    .build();
            doReturn(Optional.of(book)).when(bookRepository).findById(bookId);
            doReturn(expected).when(bookRepository).save(expected);
            assertThat(bookService.removeComment(bookId, comment)).get().isEqualTo(expected);
            verify(bookRepository).findById(bookId);
            verify(bookRepository).save(expected);
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldPassForNotExistsComment() {
            val bookId = "1";
            val comment = new Comment("e2", "t2");
            val book = Book.builder()
                    .id(bookId)
                    .name("n")
                    .comments(List.of(new Comment("e1", "t1")))
                    .build();
            doReturn(Optional.of(book)).when(bookRepository).findById(bookId);
            assertThat(bookService.removeComment(bookId, comment)).isEmpty();
            verify(bookRepository).findById(bookId);
            verifyNoMoreInteractions(bookRepository);
        }

        @Test
        void shouldFailIfBookNotExists() {
            val bookId = "1";
            doReturn(Optional.empty()).when(bookRepository).findById(bookId);
            assertThatThrownBy(() -> bookService.removeComment(bookId, new Comment("e", "t")))
                    .isInstanceOf(IllegalArgumentException.class);
            verify(bookRepository).findById(bookId);
            verifyNoMoreInteractions(bookRepository);
        }

    }

}
