package ru.yegorpilipenko.otus.spring.m3h2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yegorpilipenko.otus.spring.m3h2.model.Genre;
import ru.yegorpilipenko.otus.spring.m3h2.service.GenreService;

import java.util.Collection;

import static ru.yegorpilipenko.otus.spring.m3h2.controller.GenreController.URL;

@RestController
@RequestMapping(URL)
@RequiredArgsConstructor
public final class GenreController {

    static final String URL = "/genres";

    private final GenreService genreService;

    @GetMapping
    public Collection<Genre> getAll() {
        return genreService.getAll();
    }

}
