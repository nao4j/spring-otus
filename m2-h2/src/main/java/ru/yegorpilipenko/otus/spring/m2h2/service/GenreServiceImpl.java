package ru.yegorpilipenko.otus.spring.m2h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;
import ru.yegorpilipenko.otus.spring.m2h2.repository.BookRepository;

import javax.annotation.Nonnull;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public final class GenreServiceImpl implements GenreService {

    @Nonnull
    private final BookRepository bookRepository;

    @Nonnull
    @Override
    public Collection<Genre> getAll() {
        return bookRepository.findAllGenres();
    }

}
