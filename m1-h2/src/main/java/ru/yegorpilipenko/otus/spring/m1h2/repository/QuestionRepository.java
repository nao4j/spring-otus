package ru.yegorpilipenko.otus.spring.m1h2.repository;

import ru.yegorpilipenko.otus.spring.m1h2.domain.Question;

import java.util.Collection;

public interface QuestionRepository {

    Collection<Question> findAll();
}
