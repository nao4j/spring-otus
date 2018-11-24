package ru.yegorpilipenko.otus.spring.m2h4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h4.model.Genre;
import ru.yegorpilipenko.otus.spring.m2h4.repository.BookRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public final class GenreServiceImpl implements GenreService {

    private final BookRepository bookRepository;

    @Override
    public Collection<Genre> getAll() {
        return bookRepository.findAllGenres();
    }

}
