package ru.yegorpilipenko.otus.spring.m4h2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
public final class Comment {

    @NonNull String email;
    @NonNull String text;

    @JsonCreator
    public Comment(@JsonProperty("email") final String email, @JsonProperty("text") final String text) {
        this.email = email;
        this.text = text;
    }

}
