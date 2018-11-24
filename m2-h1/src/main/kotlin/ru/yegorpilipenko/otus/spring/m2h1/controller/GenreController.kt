package ru.yegorpilipenko.otus.spring.m2h1.controller

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.yegorpilipenko.otus.spring.m2h1.entity.Genre
import ru.yegorpilipenko.otus.spring.m2h1.service.GenreService

@ShellComponent
class GenreController(val genreService: GenreService) {

    @ShellMethod(value = "Show all genres.", group = "Genre Commands")
    fun genres(): Collection<String> = genreService.findAll().map { genre -> "${genre.id}: ${genre.name}" }

    @ShellMethod(value = "Find genre by ID.", group = "Genre Commands")
    fun genre(id: Long): String {
        val genre = genreService.findById(id)
        return if (genre != null) "${genre.id}: ${genre.name}" else "Not found"
    }

    @ShellMethod(value = "Save genre.", group = "Genre Commands")
    fun genreSave(name: String): String {
        val genre = genreService.save(Genre(name = name))
        return "${genre.id}: ${genre.name}"
    }

    @ShellMethod(value = "Remove genre by ID.", group = "Genre Commands")
    fun genreRemove(id: Long): String = if (genreService.removeById(id)) "Removed" else "Not found"

}
