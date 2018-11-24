package ru.yegorpilipenko.otus.spring.m1h5.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Scanner

@Configuration
class ContextConfiguration {

    @Bean
    fun scanner() = Scanner(System.`in`)

}