package ru.yegorpilipenko.otus.spring.m2h4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h4.model.Author;
import ru.yegorpilipenko.otus.spring.m2h4.repository.BookRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public final class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    @Override
    public Collection<Author> getAll() {
        return bookRepository.findAllAuthors();
    }

}
