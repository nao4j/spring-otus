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
import ru.yegorpilipenko.otus.spring.m2h4.model.Genre;
import ru.yegorpilipenko.otus.spring.m2h4.service.GenreService;

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
import static ru.yegorpilipenko.otus.spring.m2h4.controller.GenreController.URL;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @MockBean
    GenreService genreService;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void clean() {
        // workaround for compatibility @MockBean and @Nested
        reset(genreService);
    }

    @Nested
    class GetAllTest {

        @Test
        void getAllForNormal() throws Exception {
            val genres = List.of(new Genre("n1"), new Genre("n2"));
            doReturn(genres).when(genreService).getAll();
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json(objectMapper.writeValueAsString(genres)));
            verify(genreService).getAll();
            verifyNoMoreInteractions(genreService);
        }

        @Test
        void getAllForEmpty() throws Exception {
            doReturn(List.of()).when(genreService).getAll();
            mockMvc.perform(get(URL))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(content().json("[]"));
            verify(genreService).getAll();
            verifyNoMoreInteractions(genreService);
        }

    }

}
