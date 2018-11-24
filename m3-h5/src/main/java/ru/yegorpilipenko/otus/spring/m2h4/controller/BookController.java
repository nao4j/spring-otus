package ru.yegorpilipenko.otus.spring.m2h4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
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
import ru.yegorpilipenko.otus.spring.m2h4.resource.BookResource;
import ru.yegorpilipenko.otus.spring.m2h4.resource.ShortBookResource;
import ru.yegorpilipenko.otus.spring.m2h4.service.BookService;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static ru.yegorpilipenko.otus.spring.m2h4.controller.BookController.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(URL)
public class BookController {

    static final String URL = "/books";

    private final BookService bookService;
    private final ResourceAssemblerSupport<Book, BookResource> bookResourceAssembler;
    private final ResourceAssemblerSupport<ShortBook, ShortBookResource> shortBookResourceAssembler;

    @GetMapping
    public ResponseEntity getAll() {
        return ok().contentType(HAL_JSON_UTF8).body(shortBookResourceAssembler.toResources(bookService.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable("id") final String id) {
        return bookService.getById(id)
                .map(bookResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody final Book book) {
        return ok().contentType(HAL_JSON_UTF8).body(bookResourceAssembler.toResource(bookService.save(book)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeById(@PathVariable("id") final String id) {
        return bookService.removeById(id) ? ok().build() : notFound().build();
    }

}
