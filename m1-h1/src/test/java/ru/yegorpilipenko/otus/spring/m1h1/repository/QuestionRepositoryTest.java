package ru.yegorpilipenko.otus.spring.m1h1.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Question;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionRepositoryTest {

    @Nested
    class FindAll {

        @Test
        void shouldBePassForValidCsv() throws IOException {
            QuestionRepository questionRepository = new QuestionCsvRepository("classpath:csv/question/valid.csv");
            assertThat(questionRepository.findAll()).containsExactlyInAnyOrder(
                    new Question(0, "text1", "1", asList("1", "2", "3")),
                    new Question(1, "text2", "4", asList("4", "5", "6"))
            );
        }

        @Test
        void shouldBeFailForNotExistsCsv() {
            assertThatThrownBy(() -> new QuestionCsvRepository("classpath:csv/question/not-exists.csv"))
                    .isInstanceOf(IOException.class);
        }

        @Test
        void shouldBeFailForNullUri() {
            assertThatThrownBy(() -> new QuestionCsvRepository(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void shouldBeFailForBadDelimiter() {
            assertThatThrownBy(() -> new QuestionCsvRepository("classpath:csv/question/bad-delimiter.csv"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
