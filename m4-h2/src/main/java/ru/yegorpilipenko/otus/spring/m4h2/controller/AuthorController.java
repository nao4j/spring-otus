package ru.yegorpilipenko.otus.spring.m4h2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yegorpilipenko.otus.spring.m4h2.model.Author;
import ru.yegorpilipenko.otus.spring.m4h2.service.AuthorService;

import java.util.Collection;

import static ru.yegorpilipenko.otus.spring.m4h2.controller.AuthorController.URL;

@RestController
@RequestMapping(URL)
@RequiredArgsConstructor
public class AuthorController {

    static final String URL = "/authors";

    private final AuthorService authorService;

    @GetMapping
    public Collection<Author> getAll() {
        return authorService.getAll();
    }

}
