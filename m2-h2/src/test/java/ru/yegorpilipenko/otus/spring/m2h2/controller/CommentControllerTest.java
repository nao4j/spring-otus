package ru.yegorpilipenko.otus.spring.m2h2.controller;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yegorpilipenko.otus.spring.m2h2.document.Comment;
import ru.yegorpilipenko.otus.spring.m2h2.service.CommentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest implements WithAssertions {

    @Mock
    CommentService commentService;
    CommentController controller;

    @BeforeEach
    void setup() {
        controller = new CommentController(commentService);
    }

    @Nested
    class AddTest {

        @Test
        void shouldPassForSaveBookComment() {
            doReturn(new Comment("email", "text")).when(commentService).add(anyString(), any(Comment.class));
            assertThat(controller.save("2", "email", "text")).isEqualTo("email\ntext");
            verify(commentService).add("2", new Comment("email", "text"));
        }

    }

    @Nested
    class RemoveTest {

        @Test
        void shouldPassForExistsBookComment() {
            final String bookId = "1";
            doReturn(true).when(commentService).remove(eq(bookId), any(Comment.class));
            assertThat(controller.remove(bookId, "email", "text")).isEqualTo("Removed");
            verify(commentService).remove(bookId, new Comment("email", "text"));
            verifyNoMoreInteractions(commentService);
        }

        @Test
        void shouldPassForNotExistsBookComment() {
            final String bookId = "1";
            doReturn(false).when(commentService).remove(eq(bookId), any(Comment.class));
            assertThat(controller.remove(bookId, "email", "text")).isEqualTo("Not found");
            verify(commentService).remove(eq(bookId), any(Comment.class));
            verifyNoMoreInteractions(commentService);
        }

    }

}
