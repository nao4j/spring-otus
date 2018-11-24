package ru.yegorpilipenko.otus.spring.m1h3.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yegorpilipenko.otus.spring.m1h3.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h3.domain.Question;
import ru.yegorpilipenko.otus.spring.m1h3.service.ExaminerService;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
class ExaminerServiceIntegrationTest {

    @Autowired
    ExaminerService examinerService;

    @Test
    void shouldBePass() {
        assertThat(examinerService.getQuestions()).contains(
                new Question(
                        1,
                        "text2",
                        3,
                        asList(new Answer(1, 1, "4"),  new Answer(2, 1, "5"),  new Answer(3, 1, "6"))
                )
        );
    }

}
