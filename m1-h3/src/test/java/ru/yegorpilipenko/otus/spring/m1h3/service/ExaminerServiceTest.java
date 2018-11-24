package ru.yegorpilipenko.otus.spring.m1h3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.yegorpilipenko.otus.spring.m1h3.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h3.domain.Question;
import ru.yegorpilipenko.otus.spring.m1h3.repository.QuestionRepository;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ExaminerServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    private ExaminerService examinerService;

    @BeforeEach
    void setup() {
        initMocks(this);
        examinerService = new ExaminerServiceImpl(questionRepository);
    }

    @Nested
    class ValidateAnswers {

        private Question question1 = new Question(1, "text1", 2, asList(new Answer(1, 1, "1"), new Answer(2, 1, "2")));
        private Question question2 = new Question(2, "text2", 1, asList(new Answer(1, 2, "1"), new Answer(2, 2, "2")));

        @Test
        void shouldBePassForNormalAnswers() {
            when(questionRepository.findAll()).thenReturn(asList(question1, question2));
            assertThat(examinerService.validateAnswers(asList(new Answer(1, 1, "1"), new Answer(1, 2, "1"))))
                    .isEqualTo(1);
        }

        @Test
        void shouldBePassForEmptyAnswers() {
            when(questionRepository.findAll()).thenReturn(asList(question1, question2));
            assertThat(examinerService.validateAnswers(emptyList())).isEqualTo(0);
        }

        @Test
        void shouldBePassForExtraneousAnswers() {
            when(questionRepository.findAll()).thenReturn(asList(question1, question2));
            assertThat(examinerService.validateAnswers(singletonList(new Answer(1, 3, "1")))).isEqualTo(0);
        }
    }
}
