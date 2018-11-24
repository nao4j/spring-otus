package ru.yegorpilipenko.otus.spring.m3h4.config

import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.messaging.Message
import ru.yegorpilipenko.otus.spring.m3h4.document.Statement

@MessagingGateway
interface StatementGateway {

    @Gateway(requestChannel = "getAllStatementsInputChannel", replyChannel = "getAllStatementsOutputChannel")
    fun getAll(message: Message<*>): Collection<Statement>

    @Gateway(requestChannel = "saveStatementInputChannel", replyChannel = "saveStatementOutputChannel")
    fun save(message: Message<Statement>): Statement

}
