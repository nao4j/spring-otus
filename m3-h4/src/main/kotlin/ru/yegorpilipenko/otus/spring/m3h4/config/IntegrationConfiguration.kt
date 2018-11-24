package ru.yegorpilipenko.otus.spring.m3h4.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.IntegrationComponentScan
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.messaging.MessageChannel

@Configuration
@IntegrationComponentScan
class IntegrationConfiguration {

    @Bean
    fun getAllStatementsInputChannel(): MessageChannel = MessageChannels.publishSubscribe().get()

    @Bean
    fun getAllStatementsOutputChannel(): MessageChannel = MessageChannels.publishSubscribe().get()

    @Bean
    fun getAllStatementsFlow(): IntegrationFlow = IntegrationFlows.from("getAllStatementsInputChannel")
            .log()
            .handle("statementService", "getAll")
            .log()
            .channel("getAllStatementsOutputChannel")
            .get()


    @Bean
    fun saveStatementInputChannel(): MessageChannel = MessageChannels.publishSubscribe().get()

    @Bean
    fun saveStatementOutputChannel(): MessageChannel = MessageChannels.publishSubscribe().get()

    @Bean
    fun saveStatementFlow(): IntegrationFlow = IntegrationFlows.from("saveStatementInputChannel")
            .log()
            .handle("statementService", "save")
            .log()
            .channel("saveStatementOutputChannel")
            .get()

}
