package ru.yegorpilipenko.otus.spring.m2h3.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m2h3.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m2h3.model.Book
import ru.yegorpilipenko.otus.spring.m2h3.model.Comment
import ru.yegorpilipenko.otus.spring.m2h3.repository.BookRepository

@Service
class BookServiceImpl(val bookRepository: BookRepository): BookService {

    override fun getAll(): Collection<ShortBook> = bookRepository.findAllShort()

    override fun getById(id: String): Book? = bookRepository.findById(id).orElse(null)

    override fun save(book: Book): Book = bookRepository.save(book)

    override fun removeById(id: String): Boolean = bookRepository.removeById(id) > 0

    override fun addComment(bookId: String, comment: Comment): Book {
        val book = bookRepository.findById(bookId).orElseThrow { IllegalArgumentException() }
        return bookRepository.save(book.copy(comments = book.comments.plus(comment)))
    }

    override fun removeComment(bookId: String, comment: Comment): Book? {
        val book = bookRepository.findById(bookId).orElseThrow { IllegalArgumentException() }
        if (book.comments.contains(comment)) {
            return bookRepository.save(book.copy(comments = book.comments.minus(comment)))
        }
        return null
    }

}
