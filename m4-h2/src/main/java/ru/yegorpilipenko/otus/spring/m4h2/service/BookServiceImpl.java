package ru.yegorpilipenko.otus.spring.m4h2.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m4h2.dto.ShortBook;
import ru.yegorpilipenko.otus.spring.m4h2.model.Book;
import ru.yegorpilipenko.otus.spring.m4h2.model.Comment;
import ru.yegorpilipenko.otus.spring.m4h2.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @HystrixCommand(groupKey = "Books", commandKey = "GetAllBooks")
    public Collection<ShortBook> getAll() {
        return bookRepository.findAllShort();
    }

    @Override
    @HystrixCommand(groupKey = "Books", commandKey = "GetBookById", fallbackMethod = "defaultGetById")
    public Optional<Book> getById(final String id) {
        return bookRepository.findById(id);
    }

    private Optional<Book> defaultGetById(final String id) {
        return Optional.of(Book.builder().id("undefined").name("Колобок").build());
    }

    @Override
    @HystrixCommand(groupKey = "Books", commandKey = "SaveBook")
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    @HystrixCommand(groupKey = "Books", commandKey = "RemoveBookById")
    public boolean removeById(final String id) {
        return bookRepository.removeById(id) > 0;
    }

    @Override
    @HystrixCommand(groupKey = "Comments", commandKey = "AddComment")
    public Book addComment(final String bookId, final Comment comment) {
        val book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        val comments = new ArrayList<>(book.getComments());
        comments.add(comment);
        return bookRepository.save(withComments(book, comments));
    }

    @Override
    @HystrixCommand(groupKey = "Comments", commandKey = "RemoveComment")
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
