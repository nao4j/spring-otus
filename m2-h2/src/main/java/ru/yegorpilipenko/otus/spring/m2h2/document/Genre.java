package ru.yegorpilipenko.otus.spring.m2h2.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Document
@RequiredArgsConstructor
@NoArgsConstructor(access = PRIVATE, force = true)
public final class Genre {

    @Nonnull
    private final String name;

}
