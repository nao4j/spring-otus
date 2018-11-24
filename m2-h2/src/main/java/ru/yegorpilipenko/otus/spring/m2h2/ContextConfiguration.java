package ru.yegorpilipenko.otus.spring.m2h2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ContextConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
