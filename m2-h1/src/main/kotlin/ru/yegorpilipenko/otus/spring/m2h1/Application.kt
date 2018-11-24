package ru.yegorpilipenko.otus.spring.m2h1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
