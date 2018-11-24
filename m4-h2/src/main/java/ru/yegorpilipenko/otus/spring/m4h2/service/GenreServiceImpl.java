package ru.yegorpilipenko.otus.spring.m4h2.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m4h2.model.Genre;
import ru.yegorpilipenko.otus.spring.m4h2.repository.BookRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final BookRepository bookRepository;

    @Override
    @HystrixCommand(groupKey = "Genres", commandKey = "GetAllGenres")
    public Collection<Genre> getAll() {
        return bookRepository.findAllGenres();
    }

}
