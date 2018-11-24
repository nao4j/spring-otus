package ru.yegorpilipenko.otus.spring.m3h4.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m3h4.document.Statement
import ru.yegorpilipenko.otus.spring.m3h4.repository.StatementRepository

@Service
class StatementService(val statementRepository: StatementRepository) {

    fun getAll(): Collection<Statement> = statementRepository.findAll()

    fun save(statement: Statement): Statement = statementRepository.save(statement)

}
