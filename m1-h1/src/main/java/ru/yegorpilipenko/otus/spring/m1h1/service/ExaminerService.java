package ru.yegorpilipenko.otus.spring.m1h1.service;

import ru.yegorpilipenko.otus.spring.m1h1.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions();

    int validateAnswers(Collection<Answer> answers);
}
