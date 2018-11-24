package ru.yegorpilipenko.otus.spring.m2h1.controller

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.yegorpilipenko.otus.spring.m2h1.entity.BookComment
import ru.yegorpilipenko.otus.spring.m2h1.service.BookCommentService

@ShellComponent
class BookCommentController(val bookCommentService: BookCommentService) {

    @ShellMethod(value = "Find comment by ID.", group = "Comment Commands")
    fun comment(id: Long): String {
        val comment = bookCommentService.findById(id)
        return if (comment != null) "${comment.id}: ${comment.email}\n${comment.text}" else "Not found"
    }

    @ShellMethod(value = "Save comment.", group = "Comment Commands")
    fun commentSave(bookId: Long, email: String, text: String): String {
        val comment = bookCommentService.save(BookComment(bookId = bookId, email = email, text = text))
        return "${comment.id}: ${comment.email}\n${comment.text}"
    }

    @ShellMethod(value = "Remove comment by ID.", group = "Comment Commands")
    fun commentRemove(id: Long): String = if (bookCommentService.removeById(id)) "Removed" else "Not found"

}
