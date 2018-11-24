package ru.yegorpilipenko.otus.spring.m2h3.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.yegorpilipenko.otus.spring.m2h3.service.GenreService

@Controller
class GenreController(val genreService: GenreService) {

    @GetMapping("/genres")
    fun genres(model: Model): String {
        val genres = genreService.getAll()
        model.addAttribute("genres", genres)
        return "genres"
    }

}
