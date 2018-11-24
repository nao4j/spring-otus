package ru.yegorpilipenko.otus.spring.m2h4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.service.BookService;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static ru.yegorpilipenko.otus.spring.m2h4.controller.BookController.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(URL)
public final class BookController {

    static final String URL = "/books";

    private final BookService bookService;

    @GetMapping
    public Collection<ShortBook> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") final String id) {
        return bookService.getById(id).map(ResponseEntity::ok).orElse(notFound().build());
    }

    @PostMapping
    public Book save(@RequestBody final Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeById(@PathVariable("id") final String id) {
        return bookService.removeById(id) ? ok().build() : notFound().build();
    }

}
