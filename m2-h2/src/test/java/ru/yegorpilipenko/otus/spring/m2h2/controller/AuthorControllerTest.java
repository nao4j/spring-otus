package ru.yegorpilipenko.otus.spring.m2h2.controller;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.service.AuthorService;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest implements WithAssertions {

    @Mock
    AuthorService authorService;
    AuthorController controller;

    @BeforeEach
    void setup() {
        controller = new AuthorController(authorService);
    }

    @Nested
    class GetAllTest {

        @Test
        void shouldPassForMoreBooks() {
            doReturn(asList(new Author("af1", "al1"), new Author("af2", "al2"))).when(authorService).getAll();
            assertThat(controller.getAll()).containsExactlyInAnyOrder("af1 al1", "af2 al2");
        }

        @Test
        void shouldPassWithoutBooks() {
            doReturn(emptyList()).when(authorService).getAll();
            assertThat(controller.getAll()).isEmpty();
        }

    }

}
