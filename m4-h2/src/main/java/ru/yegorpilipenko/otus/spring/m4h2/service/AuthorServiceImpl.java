package ru.yegorpilipenko.otus.spring.m4h2.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m4h2.model.Author;
import ru.yegorpilipenko.otus.spring.m4h2.repository.BookRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    @Override
    @HystrixCommand(groupKey = "Authors", commandKey = "GetAllAuthors")
    public Collection<Author> getAll() {
        return bookRepository.findAllAuthors();
    }

}
