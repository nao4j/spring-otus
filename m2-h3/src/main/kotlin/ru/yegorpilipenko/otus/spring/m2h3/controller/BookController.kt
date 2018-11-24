package ru.yegorpilipenko.otus.spring.m2h3.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.yegorpilipenko.otus.spring.m2h3.model.Author
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.model.Comment
import ru.yegorpilipenko.otus.spring.m2h3.model.Genre
import ru.yegorpilipenko.otus.spring.m2h3.service.BookService

@Controller
class BookController(val bookService: BookService) {

    @GetMapping(value = ["/", "/books"])
    fun books(model: Model): String {
        val books = bookService.getAll()
        model.addAttribute("books", books)
        return "books"
    }

    @GetMapping("/books/{id}")
    fun book(@PathVariable("id") id: String, model: Model): String {
        val book = bookService.getById(id)
        return if (book != null) {
            model.addAttribute("book", book)
            return "book"
        } else {
            "error"
        }
    }

    @GetMapping("/books/add")
    fun save(): String {
        return "save"
    }

    @PostMapping("/books/save")
    fun save(
            @RequestParam("name") name: String,
            @RequestParam("authors") authorsArray: String,
            @RequestParam("genres") genresArray: String,
            model: Model
    ): String {
        val authors = authorsArray.split(Regex("\\s*,\\s*"))
                .asSequence()
                .map { author -> author.split(Regex(" +")) }
                .map{ authorParts -> Author(authorParts[0], authorParts[1]) }
                .toSet()
        val genres = genresArray.split(Regex("\\s*,\\s*"))
                .asSequence()
                .map{ genreName -> Genre(genreName) }
                .toSet()
        bookService.save(Book(name = name, authors = authors, genres = genres))
        return books(model)
    }

    @GetMapping("/books/{id}/delete")
    fun delete(@PathVariable("id") id: String, model: Model): String {
        return if (bookService.removeById(id)) {
            books(model)
        } else {
            "error"
        }
    }

    @PostMapping("/books/{bookId}/addComment")
    fun addComment(
            @PathVariable("bookId") bookId: String,
            comment: Comment,
            model: Model
    ): String {
        bookService.addComment(bookId, comment)
        return book(bookId, model)
    }

    @GetMapping("/books/{bookId}/deleteComment")
    fun deleteComment(
            @PathVariable("bookId") bookId: String,
            @RequestParam("email") email: String,
            @RequestParam("text") text: String,
            model: Model
    ): String {
        return if (bookService.removeComment(bookId, Comment(email, text)) != null) {
            book(bookId, model)
        } else {
            "error"
        }
    }

}
