package ru.yegorpilipenko.otus.spring.m3h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m3h2.model.Author;
import ru.yegorpilipenko.otus.spring.m3h2.repository.BookRepository;

import java.util.Collection;

import static ru.yegorpilipenko.otus.spring.m3h2.model.User.Role.ADMIN;
import static ru.yegorpilipenko.otus.spring.m3h2.model.User.Role.USER;

@Service
@Secured({ADMIN, USER})
@RequiredArgsConstructor
public final class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    @Override
    public Collection<Author> getAll() {
        return bookRepository.findAllAuthors();
    }

}
