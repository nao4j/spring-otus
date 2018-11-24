package ru.yegorpilipenko.otus.spring.m2h3.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.yegorpilipenko.otus.spring.m2h3.service.AuthorService

@Controller
class AuthorController(val authorService: AuthorService) {

    @GetMapping("/authors")
    fun authors(model: Model): String {
        val authors = authorService.getAll()
        model.addAttribute("authors", authors)
        return "authors"
    }

}
