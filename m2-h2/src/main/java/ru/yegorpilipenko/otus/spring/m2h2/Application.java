package ru.yegorpilipenko.otus.spring.m2h2;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Nonnull;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application {

    public static void main(@Nonnull final String... args) {
        run(Application.class, args);
    }
}
