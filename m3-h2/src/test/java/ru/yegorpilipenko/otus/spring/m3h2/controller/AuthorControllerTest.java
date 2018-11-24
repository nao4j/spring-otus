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
import ru.yegorpilipenko.otus.spring.m3h2.model.Author;
import ru.yegorpilipenko.otus.spring.m3h2.service.AuthorService;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.yegorpilipenko.otus.spring.m3h2.controller.AuthorController.URL;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @MockBean
    AuthorService authorService;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void clean() {
        // workaround for compatibility @MockBean and @Nested
        reset(authorService);
    }

    @Nested
    @WithMockUser
    class GetAllTest {

        @Test
        void getAllForNormal() throws Exception {
            val authors = List.of(new Author("f1", "l1"), new Author("f2", "l2"));
            doReturn(authors).when(authorService).getAll();
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(authors)));
            verify(authorService).getAll();
            verifyNoMoreInteractions(authorService);
        }

        @Test
        void getAllForEmpty() throws Exception {
            doReturn(List.of()).when(authorService).getAll();
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json("[]"));
            verify(authorService).getAll();
            verifyNoMoreInteractions(authorService);
        }

    }

}
