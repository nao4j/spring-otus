package ru.yegorpilipenko.otus.spring.m2h2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;
import ru.yegorpilipenko.otus.spring.m2h2.service.GenreService;

import javax.annotation.Nonnull;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

@ShellComponent
@RequiredArgsConstructor
public final class GenreController extends AbstractShellController {

    @Nonnull
    private final GenreService genreService;

    @Nonnull
    @ShellMethod(key = "genres", value = "Show all genres.", group = "Genre Commands")
    public Collection<String> getAll() {
        return genreService.getAll().stream().map(this::toView).collect(toList());
    }

    @Nonnull
    private String toView(@Nonnull Genre genre) {
        return genre.getName();
    }

}
