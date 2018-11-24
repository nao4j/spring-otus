package ru.yegorpilipenko.otus.spring.m2h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.repository.BookRepository;

import javax.annotation.Nonnull;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public final class AuthorServiceImpl implements AuthorService {

    @Nonnull
    private final BookRepository bookRepository;

    @Nonnull
    @Override
    public Collection<Author> getAll() {
        return bookRepository.findAllAuthors();
    }

}
