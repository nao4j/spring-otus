package ru.yegorpilipenko.otus.spring.m2h2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h2.document.Book;
import ru.yegorpilipenko.otus.spring.m2h2.document.Comment;
import ru.yegorpilipenko.otus.spring.m2h2.repository.BookRepository;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public final class CommentServiceImpl implements CommentService {

    @Nonnull
    private final BookRepository bookRepository;

    @Nonnull
    @Override
    public Comment add(@Nonnull String bookId, @Nonnull final Comment comment) {
        final Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        book.getComments().add(comment);
        bookRepository.save(book);
        return comment;
    }

    @Override
    public boolean remove(@Nonnull String bookId, @Nonnull final Comment comment) {
        final Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        final List<Comment> comments = new ArrayList<>(book.getComments());
        if (comments.remove(comment)) {
            bookRepository.save(new Book(book.getId(), book.getName(), book.getAuthors(), book.getGenres(), comments));
            return true;
        }
        return false;
    }

}
