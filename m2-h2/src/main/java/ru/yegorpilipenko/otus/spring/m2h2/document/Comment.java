package ru.yegorpilipenko.otus.spring.m2h2.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

import static lombok.AccessLevel.PRIVATE;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = PRIVATE, force = true)
public final class Comment {

    @Nonnull
    private final String email;
    @Nonnull
    private final String text;

}
