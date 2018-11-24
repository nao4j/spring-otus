package ru.yegorpilipenko.otus.spring.m4h2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
public final class Author {

    @NonNull String firstName;
    @NonNull String lastName;

    @JsonCreator
    public Author(@JsonProperty("firstName") final String firstName, @JsonProperty("lastName") final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
