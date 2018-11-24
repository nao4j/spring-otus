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
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m2h4.model.Author;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;
import ru.yegorpilipenko.otus.spring.m2h4.model.Genre;
import ru.yegorpilipenko.otus.spring.m2h4.resource.BookResource;
import ru.yegorpilipenko.otus.spring.m2h4.resource.ShortBookResource;
import ru.yegorpilipenko.otus.spring.m2h4.service.BookService;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.yegorpilipenko.otus.spring.m2h4.controller.BookController.URL;

@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @MockBean
    BookService bookService;
    @MockBean
    ResourceAssemblerSupport<Book, BookResource> bookResourceAssembler;
    @MockBean
    ResourceAssemblerSupport<ShortBook, ShortBookResource> shortBookResourceAssembler;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper().setDefaultPropertyInclusion(NON_EMPTY);

    @AfterEach
    void clean() {
        // workaround for compatibility @MockBean and @Nested
        reset(bookService, bookResourceAssembler, shortBookResourceAssembler);
    }

    @Nested
    class GetAllTest {

        @Test
        void shouldPassForExistsBooks() throws Exception {
            val books = List.of(new ShortBookResource("n1"), new ShortBookResource("n2"));
            doReturn(null).when(bookService).getAll();
            doReturn(books).when(shortBookResourceAssembler).toResources(any());
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(books)));
            verify(bookService).getAll();
            verify(shortBookResourceAssembler).toResources(any());
            verifyNoMoreInteractions(bookService, shortBookResourceAssembler);
        }

        @Test
        void shouldPassForNotExistsBooks() throws Exception {
            doReturn(null).when(bookService).getAll();
            doReturn(List.of()).when(shortBookResourceAssembler).toResources(any());
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8))
                    .andExpect(content().json("[]"));
            verify(bookService).getAll();
            verify(shortBookResourceAssembler).toResources(any());
            verifyNoMoreInteractions(bookService, shortBookResourceAssembler);
        }

    }

    @Nested
    class GetByIdTest {

        @Test
        void shouldPassForExistsBook() throws Exception {
            val bookId = "1";
            val book = new BookResource(
                    "n",
                    List.of(new Author("f", "l")),
                    List.of(new Genre("gn")),
                    List.of(new Comment("e", "t"))
            );
            doReturn(Optional.of(Book.builder().build())).when(bookService).getById(bookId);
            doReturn(book).when(bookResourceAssembler).toResource(any());
            mockMvc.perform(get("{base}/{bookId}", URL, bookId))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(book)));
            verify(bookService).getById(bookId);
            verify(bookResourceAssembler).toResource(any());
            verifyNoMoreInteractions(bookService, bookResourceAssembler);
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
            verifyNoMoreInteractions(bookService, bookResourceAssembler);
        }

    }

    @Nested
    class SaveTest {

        @Test
        void shouldPass() throws Exception {
            val book = new BookResource(
                    "n",
                    List.of(new Author("f", "l")),
                    List.of(new Genre("gn")),
                    List.of(new Comment("e", "t"))
            );
            doReturn(null).when(bookService).save(any());
            doReturn(book).when(bookResourceAssembler).toResource(any());
            mockMvc.perform(
                    post(URL)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(book))
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(book)));
        }

    }

    @Nested
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
