package ru.yegorpilipenko.otus.spring.m2h2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;
import ru.yegorpilipenko.otus.spring.m2h2.service.BookService;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Scanner;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@ShellComponent
@RequiredArgsConstructor
public final class BookController extends AbstractShellController {

    @Nonnull
    private final Scanner scanner;
    @Nonnull
    private final BookService bookService;

    @Nonnull
    @ShellMethod(key = "books", value = "Show all books.", group = "Book Commands")
    public Collection<String> getAll() {
        return bookService.getAll().stream().map(this::toShortView).collect(toList());
    }

    @Nonnull
    @ShellMethod(key = "book", value = "Find book by ID.", group = "Book Commands")
    public String getById(@Nonnull String id) {
        return bookService.getById(id).map(this::toView).orElse(NOT_FOUND_MESSAGE);
    }

    @Nonnull
    @SuppressWarnings("squid:S106")
    @ShellMethod(key = "book-save", value = "Save book.", group = "Book Commands")
    public String save(@Nonnull String name) {
        System.out.println("Перечислите имена авторов через запятую:");
        final Collection<Author> authors = readLineAsEntities().stream()
                .map(authorLine -> authorLine.split(" +"))
                .map(authorParts -> new Author(authorParts[0], authorParts[1]))
                .collect(toList());

        System.out.println("Перечислите названия жанров через запятую:");
        final Collection<Genre> genres = readLineAsEntities().stream().map(Genre::new).collect(toList());

        final Book book = bookService.save(name, authors, genres);
        return toView(book);
    }

    @Nonnull
    @ShellMethod(key = "book-remove", value = "Remove book by ID.", group = "Book Commands")
    public String remove(@Nonnull String id) {
        return bookService.remove(id) ? REMOVE_SUCCESS_MESSAGE : NOT_FOUND_MESSAGE;
    }

    @Nonnull
    private Collection<String> readLineAsEntities() {
        return asList(scanner.nextLine().split("\\s*,\\s*"));
    }

    @Nonnull
    private String toShortView(@Nonnull Book book) {
        return format("%s: %s", book.getId(), book.getName());
    }

    @Nonnull
    private String toView(@Nonnull Book book) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(book.getId()).append(": ").append(book.getName());
        if (!book.getAuthors().isEmpty()) {
            stringBuilder.append("\nАвторы:");
            book.getAuthors().forEach(author -> stringBuilder.append("\n")
                    .append(author.getFirstName()).append(" ").append(author.getLastName())
            );
        }
        if (!book.getGenres().isEmpty()) {
            stringBuilder.append("\nЖанры:");
            book.getGenres().forEach(genre -> stringBuilder.append("\n").append(genre.getName()));
        }
        if (!book.getComments().isEmpty()) {
            stringBuilder.append("\nКомментарии:");
            book.getComments().forEach(comment -> stringBuilder.append("\n")
                    .append(comment.getEmail()).append("\n").append(comment.getText()));
        }
        return stringBuilder.toString();
    }

}
