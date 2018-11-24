package ru.yegorpilipenko.otus.spring.m2h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h2.document.Author;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Genre;
import ru.yegorpilipenko.otus.spring.m2h2.repository.BookRepository;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class BookServiceImpl implements BookService {

    @Nonnull
    private final BookRepository bookRepository;

    @Nonnull
    @Override
    public Collection<Book> getAll() {
        return bookRepository.findAll();
    }

    @Nonnull
    @Override
    public Optional<Book> getById(@Nonnull final String id) {
        return bookRepository.findById(id);
    }

    @Nonnull
    @Override
    public Book save(
            @Nonnull final String name,
            @Nonnull final Collection<Author> authors,
            @Nonnull final Collection<Genre> genres
    ) {
        final Book book = new Book(name, authors, genres);
        return bookRepository.save(book);
    }

    @Override
    public boolean remove(@Nonnull final String id) {
        return bookRepository.removeById(id) > 0;
    }

}
