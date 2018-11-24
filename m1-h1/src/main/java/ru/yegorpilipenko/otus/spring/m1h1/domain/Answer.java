package ru.yegorpilipenko.otus.spring.m1h1.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class Answer {

    private final long questionId;
    @NonNull private final String answerText;
}
