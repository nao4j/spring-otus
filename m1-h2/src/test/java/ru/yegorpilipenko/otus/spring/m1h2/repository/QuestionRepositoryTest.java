package ru.yegorpilipenko.otus.spring.m1h2.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.yegorpilipenko.otus.spring.m1h2.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h2.domain.Question;

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
                    new Question(
                            0,
                            "text1",
                            1,
                            asList(new Answer(1, 0, "1"), new Answer(2, 0, "2"), new Answer(3, 0, "3"))
                    ),
                    new Question(
                            1,
                            "text2",
                            3,
                            asList(new Answer(1, 1, "4"), new Answer(2, 1, "5"), new Answer(3, 1, "6"))
                    )
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
