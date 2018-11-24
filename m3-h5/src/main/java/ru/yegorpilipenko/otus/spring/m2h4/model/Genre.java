package ru.yegorpilipenko.otus.spring.m2h4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
public final class Genre {

    @NonNull String name;

    @JsonCreator
    public Genre(@JsonProperty("name") final String name) {
        this.name = name;
    }

}
