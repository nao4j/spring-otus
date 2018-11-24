package ru.yegorpilipenko.otus.spring.m1h4.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionCsvRepository;
import ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionRepository;
import ru.yegorpilipenko.otus.spring.m1h4.service.ExaminerService;
import ru.yegorpilipenko.otus.spring.m1h4.service.ExaminerServiceImpl;

import java.io.IOException;

@Configuration
public class IntegrationTestConfig {

    @Bean
    public QuestionRepository questionRepository() throws IOException {
        return new QuestionCsvRepository("classpath:csv/question/valid.csv");
    }

    @Bean
    public ExaminerService examinerService(final QuestionRepository questionRepository) {
        return new ExaminerServiceImpl(questionRepository);
    }

}
