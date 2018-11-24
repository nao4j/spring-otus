package ru.yegorpilipenko.otus.spring.m1h3.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableList;

@Data
public class Question {

    private final long id;
    @NonNull private final String formulation;
    private final long answerId;
    private final Collection<Answer> options;

    public Question(final long id, final String formulation, final long answerId, final Collection<Answer> options) {
        this.id = id;
        this.formulation = formulation;
        this.answerId = answerId;
        this.options = unmodifiableList(new ArrayList<>(options));
    }
}
