package ru.yegorpilipenko.otus.spring.m3h2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.yegorpilipenko.otus.spring.m3h2.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m3h2.model.Author;
import ru.yegorpilipenko.otus.spring.m3h2.model.Book;
import ru.yegorpilipenko.otus.spring.m3h2.model.Comment;
import ru.yegorpilipenko.otus.spring.m3h2.model.Genre;
import ru.yegorpilipenko.otus.spring.m3h2.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.yegorpilipenko.otus.spring.m3h2.controller.BookController.URL;

@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
class BookControllerTest {

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
    @WithMockUser
    class GetAllTest {

        @Test
        void shouldPassForExistsBooks() throws Exception {
            val books = List.of(new ShortBook("1", "n1"), new ShortBook("2", "n2"));
            doReturn(books).when(bookService).getAll();
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(books)));
            verify(bookService).getAll();
            verifyNoMoreInteractions(bookService);
        }

        @Test
        void shouldPassForNotExistsBooks() throws Exception {
            doReturn(List.of()).when(bookService).getAll();
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json("[]"));
            verify(bookService).getAll();
            verifyNoMoreInteractions(bookService);
        }

    }

    @Nested
    @WithMockUser
    class GetByIdTest {

        @Test
        void shouldPassForExistsBook() throws Exception {
            val bookId = "1";
            val book = Book.builder()
                    .id(bookId)
                    .name("n")
                    .authors(List.of(new Author("f", "l")))
                    .genres(List.of(new Genre("gn")))
                    .comments(List.of(new Comment("e", "t")))
                    .build();
            doReturn(Optional.of(book)).when(bookService).getById(bookId);
            mockMvc.perform(get("{base}/{bookId}", URL, bookId))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(book)));
            verify(bookService).getById(bookId);
            verifyNoMoreInteractions(bookService);
        }

        @Test
        void shouldPassForNotExistsBook() throws Exception {
            val bookId = "1";
            doReturn(Optional.empty()).when(bookService).getById(bookId);
            mockMvc.perform(get("{base}/{bookId}", URL, bookId))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(isEmptyString()));
            verify(bookService).getById(bookId);
            verifyNoMoreInteractions(bookService);
        }

    }

    @Nested
    @WithMockUser
    class SaveTest {

        @Test
        void shouldPass() throws Exception {
            val book = Book.builder()
                    .id("1")
                    .name("n")
                    .authors(List.of(new Author("f", "l")))
                    .genres(List.of(new Genre("gn")))
                    .comments(List.of(new Comment("e", "t")))
                    .build();
            doReturn(book).when(bookService).save(book);
            mockMvc.perform(
                    post(URL)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(book))
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(book)));
        }

    }

    @Nested
    @WithMockUser
    class RemoveByIdTest {

        @Test
        void shouldPassForExistsBook() throws Exception {
            val bookId = "1";
            doReturn(true).when(bookService).removeById(bookId);
            mockMvc.perform(delete("{base}/{bookId}", URL, bookId))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(isEmptyString()));
            verify(bookService).removeById(bookId);
            verifyNoMoreInteractions(bookService);
        }

        @Test
        void shouldPassForNotExistsBook() throws Exception {
            val bookId = "1";
            doReturn(false).when(bookService).removeById(bookId);
            mockMvc.perform(delete("{base}/{bookId}", URL, bookId))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(isEmptyString()));
            verify(bookService).removeById(bookId);
            verifyNoMoreInteractions(bookService);
        }

    }

}
