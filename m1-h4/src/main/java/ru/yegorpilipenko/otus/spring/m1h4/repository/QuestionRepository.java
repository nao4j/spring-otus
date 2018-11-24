package ru.yegorpilipenko.otus.spring.m1h4.repository;

import ru.yegorpilipenko.otus.spring.m1h4.domain.Question;

import java.util.Collection;

public interface QuestionRepository {

    Collection<Question> findAll();
}
