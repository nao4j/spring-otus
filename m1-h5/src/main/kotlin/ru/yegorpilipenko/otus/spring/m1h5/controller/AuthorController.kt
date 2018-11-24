package ru.yegorpilipenko.otus.spring.m1h5.controller

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.yegorpilipenko.otus.spring.m1h5.model.Author
import ru.yegorpilipenko.otus.spring.m1h5.service.AuthorService

@ShellComponent
class AuthorController(val authorService: AuthorService) {

    @ShellMethod(value = "Show all authors.", group = "Author Commands")
    fun authors(): Collection<String>
            = authorService.findAll().map { author -> "${author.id}: ${author.firstName} ${author.lastName}" }

    @ShellMethod(value = "Find author by ID.", group = "Author Commands")
    fun author(id: Long): String {
        val author = authorService.findById(id)
        return if (author != null) "${author.id}: ${author.firstName} ${author.lastName}" else "Not found"
    }

    @ShellMethod(value = "Save author.", group = "Author Commands")
    fun authorSave(firstName: String, lastName: String): String {
        val author = authorService.save(Author(firstName = firstName, lastName = lastName))
        return "${author.id}: ${author.firstName} ${author.lastName}"
    }

    @ShellMethod(value = "Remove author by ID.", group = "Author Commands")
    fun authorRemove(id: Long): String = if (authorService.removeById(id)) "Removed" else "Not found"

}
