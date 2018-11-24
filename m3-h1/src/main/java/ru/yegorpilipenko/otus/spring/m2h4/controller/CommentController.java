package ru.yegorpilipenko.otus.spring.m2h4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;
import ru.yegorpilipenko.otus.spring.m2h4.service.BookService;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static ru.yegorpilipenko.otus.spring.m2h4.controller.CommentController.URL;

@RestController
@RequestMapping(URL)
@RequiredArgsConstructor
public final class CommentController {

    static final String URL = "/comments";

    private final BookService bookService;

    @PostMapping("{bookId}")
    public ResponseEntity<Book> add(@PathVariable("bookId") final String bookId, @RequestBody final Comment comment) {
        final Book book;
        try {
            book = bookService.addComment(bookId, comment);
        } catch (IllegalArgumentException e) {
            return badRequest().build();
        }
        return ok(book);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Book> remove(
            @PathVariable("bookId") final String bookId,
            @RequestBody final Comment comment
    ) {
        final Optional<Book> book;
        try {
            book = bookService.removeComment(bookId, comment);
        } catch (IllegalArgumentException e) {
            return badRequest().build();
        }
        return book.map(ResponseEntity::ok).orElse(notFound().build());
    }

}
