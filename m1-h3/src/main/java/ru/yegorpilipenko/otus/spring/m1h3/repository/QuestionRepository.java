package ru.yegorpilipenko.otus.spring.m1h3.repository;

import ru.yegorpilipenko.otus.spring.m1h3.domain.Question;

import java.util.Collection;

public interface QuestionRepository {

    Collection<Question> findAll();
}
