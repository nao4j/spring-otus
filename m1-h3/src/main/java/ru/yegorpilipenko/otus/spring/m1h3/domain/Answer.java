package ru.yegorpilipenko.otus.spring.m1h3.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class Answer {

    private final long id;
    private final long questionId;
    @NonNull private final String answerText;
}
