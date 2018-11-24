package ru.yegorpilipenko.otus.spring.m3h4.controller

import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yegorpilipenko.otus.spring.m3h4.config.StatementGateway
import ru.yegorpilipenko.otus.spring.m3h4.document.Statement

@RestController
@RequestMapping("/statements")
class StatementController(val statementGateway: StatementGateway) {

    @GetMapping
    fun getAll() = statementGateway.getAll(MessageBuilder.withPayload("getAllStatements").build())

    @PostMapping
    fun save(@RequestBody statement: Statement) = statementGateway.save(MessageBuilder.withPayload(statement).build())

}
