package ru.yegorpilipenko.otus.spring.m2h4;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.boot.SpringApplication.run;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

@SpringBootApplication
@EnableHypermediaSupport(type = HAL)
public class Application {

    public static void main(final String... args) {
        run(Application.class, args);
    }

}
