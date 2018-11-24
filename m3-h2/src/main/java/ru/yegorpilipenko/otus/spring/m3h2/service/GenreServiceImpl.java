package ru.yegorpilipenko.otus.spring.m3h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m3h2.model.Genre;
import ru.yegorpilipenko.otus.spring.m3h2.repository.BookRepository;

import java.util.Collection;

import static ru.yegorpilipenko.otus.spring.m3h2.model.User.Role.ADMIN;
import static ru.yegorpilipenko.otus.spring.m3h2.model.User.Role.USER;

@Service
@Secured({ADMIN, USER})
@RequiredArgsConstructor
public final class GenreServiceImpl implements GenreService {

    private final BookRepository bookRepository;

    @Override
    public Collection<Genre> getAll() {
        return bookRepository.findAllGenres();
    }

}
