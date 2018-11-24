package ru.yegorpilipenko.otus.spring.m2h4.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h4.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;
import ru.yegorpilipenko.otus.spring.m2h4.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Collection<ShortBook> getAll() {
        return bookRepository.findAllShort();
    }

    @Override
    public Optional<Book> getById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean removeById(final String id) {
        return bookRepository.removeById(id) > 0;
    }

    @Override
    public Book addComment(final String bookId, final Comment comment) {
        val book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        val comments = new ArrayList<>(book.getComments());
        comments.add(comment);
        return bookRepository.save(withComments(book, comments));
    }

    @Override
    public Optional<Book> removeComment(final String bookId, final Comment comment) {
        val book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        if (book.getComments().contains(comment)) {
            val comments = new ArrayList<>(book.getComments());
            comments.remove(comment);
            return Optional.of(bookRepository.save(withComments(book, comments)));
        }
        return Optional.empty();
    }

    private Book withComments(final Book book, final Collection<Comment> comments) {
        return Book.builder()
                .id(book.getId())
                .name(book.getName())
                .authors(book.getAuthors())
                .genres(book.getGenres())
                .comments(comments)
                .build();
    }

}
