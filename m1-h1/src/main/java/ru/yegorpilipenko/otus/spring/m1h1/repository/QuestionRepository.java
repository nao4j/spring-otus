package ru.yegorpilipenko.otus.spring.m1h1.repository;

import ru.yegorpilipenko.otus.spring.m1h1.domain.Question;

import java.util.Collection;

public interface QuestionRepository {

    Collection<Question> findAll();
}
