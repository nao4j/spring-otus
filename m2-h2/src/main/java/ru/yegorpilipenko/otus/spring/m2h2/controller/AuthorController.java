package ru.yegorpilipenko.otus.spring.m2h2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.service.AuthorService;

import javax.annotation.Nonnull;
import java.util.Collection;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@ShellComponent
@RequiredArgsConstructor
public final class AuthorController extends AbstractShellController {

    @Nonnull
    private final AuthorService authorService;

    @Nonnull
    @ShellMethod(key = "authors", value = "Show all authors.", group = "Author Commands")
    public Collection<String> getAll() {
        return authorService.getAll().stream().map(this::toView).collect(toList());
    }

    @Nonnull
    private String toView(@Nonnull Author author) {
        return format("%s %s", author.getFirstName(), author.getLastName());
    }

}
