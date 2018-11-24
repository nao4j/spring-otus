package ru.yegorpilipenko.otus.spring.m1h1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@ImportResource("classpath:context.xml")
public class Application {

    public static void main(final String... args) {
        run(Application.class, args);
    }
}
