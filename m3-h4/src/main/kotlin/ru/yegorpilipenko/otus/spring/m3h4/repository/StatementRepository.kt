package ru.yegorpilipenko.otus.spring.m3h4.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.yegorpilipenko.otus.spring.m3h4.document.Statement

interface StatementRepository: MongoRepository<Statement, String>
