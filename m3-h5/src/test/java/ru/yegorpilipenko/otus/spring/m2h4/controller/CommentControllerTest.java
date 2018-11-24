package ru.yegorpilipenko.otus.spring.m2h4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;
import ru.yegorpilipenko.otus.spring.m2h4.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.yegorpilipenko.otus.spring.m2h4.controller.CommentController.URL;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @MockBean
    BookService bookService;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void clean() {
        // workaround for compatibility @MockBean and @Nested
        reset(bookService);
    }

    @Nested
    class AddTest {

        @Test
        void shouldPassForExistsBook() throws Exception {
            val bookId = "1";
            val comment = new Comment("e", "t");
            val expected = Book.builder()
                    .id(bookId)
                    .name("n")
                    .comments(List.of(comment))
                    .build();
            doReturn(expected).when(bookService).addComment(bookId, comment);
            mockMvc.perform(
                    post("{base}/{bookId}", URL, bookId)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(comment))
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
            verify(bookService).addComment(bookId, comment);
            verifyNoMoreInteractions(bookService);
        }

        @Test
        void shouldFailForNotExistsBook() throws Exception {
            val bookId = "1";
            val comment = new Comment("e", "t");
            doThrow(IllegalArgumentException.class).when(bookService).addComment(bookId, comment);
            mockMvc.perform(
                    post("{base}/{bookId}", URL, bookId)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(comment))
            ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(isEmptyString()));
            verify(bookService).addComment(bookId, comment);
            verifyNoMoreInteractions(bookService);
        }

    }

    @Nested
    class RemoveTest {

        @Test
        void shouldPassForExistsComment() throws Exception {
            val bookId = "1";
            val comment = new Comment("e", "t");
            val expected = Book.builder().id(bookId).name("n").build();
            doReturn(Optional.of(expected)).when(bookService).removeComment(bookId, comment);
            mockMvc.perform(
                    delete("{base}/{bookId}", URL, bookId)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(comment))
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
            verify(bookService).removeComment(bookId, comment);
            verifyNoMoreInteractions(bookService);
        }

        @Test
        void shouldPassForNotExistsComment() throws Exception {
            val bookId = "1";
            val comment = new Comment("e", "t");
            doReturn(Optional.empty()).when(bookService).removeComment(bookId, comment);
            mockMvc.perform(
                    delete("{base}/{bookId}", URL, bookId)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(comment))
            ).andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(isEmptyString()));
            verify(bookService).removeComment(bookId, comment);
            verifyNoMoreInteractions(bookService);
        }

        @Test
        void shouldFailForNotExistsBook() throws Exception {
            val bookId = "1";
            val comment = new Comment("e", "t");
            doThrow(IllegalArgumentException.class).when(bookService).removeComment(bookId, comment);
            mockMvc.perform(
                    delete("{base}/{bookId}", URL, bookId)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(comment))
            ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(isEmptyString()));
            verify(bookService).removeComment(bookId, comment);
            verifyNoMoreInteractions(bookService);
        }

    }

}
