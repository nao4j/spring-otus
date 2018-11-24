package ru.yegorpilipenko.otus.spring.m3h3.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yegorpilipenko.otus.spring.m3h3.service.MigrationService

@RestController
@RequestMapping("/migration")
class MigrationController(val migrationService: MigrationService) {

    @PostMapping("toDb")
    fun migrateToDb() = migrationService.migrateToDb()

}
