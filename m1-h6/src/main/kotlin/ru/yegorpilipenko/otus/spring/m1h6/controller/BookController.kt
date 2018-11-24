package ru.yegorpilipenko.otus.spring.m1h6.controller

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.yegorpilipenko.otus.spring.m1h6.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h6.entity.Book
import ru.yegorpilipenko.otus.spring.m1h6.service.BookService
import java.util.Scanner

@ShellComponent
class BookController(val bookService: BookService, val scanner: Scanner) {

    @ShellMethod(value = "Show all books.", group = "Book Commands")
    fun books(): Collection<String> = bookService.findAll().map { book -> "${book.id}: ${book.name}" }

    @ShellMethod(value = "Find book by ID.", group = "Book Commands")
    fun book(id: Long): String {
        val book: Book = bookService.findById(id)
                ?: return "Not found"
        return bookView(book)
    }

    @ShellMethod(value = "Save book.", group = "Book Commands")
    fun bookSave(name: String): String {
        println("Перечислите идентификаторы авторов:")
        val authorIds: List<Long> = scanner.nextLine().split(" ").map { it.toLong() }
        println("Перечислите идентификаторы жанров:")
        val genreIds: List<Long> = scanner.nextLine().split(" ").map { it.toLong() }
        val book = bookService.save(ShortBook(name = name), authorIds, genreIds)
        return bookView(book)
    }

    @ShellMethod(value = "Remove book by ID.", group = "Book Commands")
    fun bookRemove(id: Long): String = if (bookService.removeById(id)) "Removed" else "Not found"

    private fun bookView(book: Book): String {
        val bookBuilder = StringBuilder()
        bookBuilder.append(book.id).append(": ").append(book.name)
        if (book.authors.isNotEmpty()) {
            bookBuilder.append("\nАвторы:")
            book.authors.forEach {
                author -> bookBuilder.append("\n")
                    .append(author.id)
                    .append(": ")
                    .append(author.firstName).append(" ").append(author.lastName)
            }
        }
        if (book.genres.isNotEmpty()) {
            bookBuilder.append("\nЖанры:")
            book.genres.forEach { genre -> bookBuilder.append("\n").append(genre.id).append(": ").append(genre.name) }
        }
        if (book.comments.isNotEmpty()) {
            bookBuilder.append("\nКомментарии:")
            book.comments.forEach { comment ->
                bookBuilder.append("\n")
                        .append(comment.id).append(": ").append(comment.email)
                        .append("\n")
                        .append(comment.text)
            }
        }
        return bookBuilder.toString()
    }

}
