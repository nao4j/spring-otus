package ru.yegorpilipenko.otus.spring.m1h1.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableList;

@Data
public class Question {

    private final long id;
    @NonNull private final String formulation;
    @NonNull private final String answer;
    private final Collection<String> options;

    public Question(final long id, final String formulation, final String answer, final Collection<String> options) {
        this.id = id;
        this.formulation = formulation;
        this.answer = answer;
        this.options = unmodifiableList(new ArrayList<>(options));

        if (!this.options.contains(answer)) {
            throw new IllegalArgumentException("Options must be contains the answer");
        }
    }
}
