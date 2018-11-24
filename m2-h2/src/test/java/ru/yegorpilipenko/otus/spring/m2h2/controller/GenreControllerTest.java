package ru.yegorpilipenko.otus.spring.m2h2.controller;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;
import ru.yegorpilipenko.otus.spring.m2h2.service.GenreService;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest implements WithAssertions {

    @Mock
    GenreService genreService;
    GenreController controller;

    @BeforeEach
    void setup() {
        controller = new GenreController(genreService);
    }

    @Nested
    class GetAllTest {

        @Test
        void shouldPassForMoreBooks() {
            doReturn(asList(new Genre("b1"), new Genre("b2"))).when(genreService).getAll();
            assertThat(controller.getAll()).containsExactlyInAnyOrder("b1", "b2");
        }

        @Test
        void shouldPassWithoutBooks() {
            doReturn(emptyList()).when(genreService).getAll();
            assertThat(controller.getAll()).isEmpty();
        }

    }

}
