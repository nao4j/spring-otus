package ru.yegorpilipenko.otus.spring.m2h1.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Propagation.SUPPORTS
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m2h1.entity.Author
import ru.yegorpilipenko.otus.spring.m2h1.entity.Book
import ru.yegorpilipenko.otus.spring.m2h1.entity.Genre
import ru.yegorpilipenko.otus.spring.m2h1.repository.AuthorRepository
import ru.yegorpilipenko.otus.spring.m2h1.repository.BookRepository
import ru.yegorpilipenko.otus.spring.m2h1.repository.GenreRepository

@Service
class BookServiceImpl(
        val bookRepository: BookRepository,
        val authorRepository: AuthorRepository,
        val genreRepository: GenreRepository
) : BookService {

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findAll(): Collection<Book> = bookRepository.findAll()

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findById(id: Long): Book? = bookRepository.findById(id).orElse(null)

    @Transactional(propagation = REQUIRED)
    override fun save(name: String, authorIds: Collection<Long>, genreIds: Collection<Long>): Book {
        val authors: Collection<Author> = authorRepository.findAllById(authorIds)
        val genres: Collection<Genre> = genreRepository.findAllById(genreIds)
        return bookRepository.save(Book(name = name, authors = authors, genres = genres))
    }

    @Transactional(propagation = REQUIRED)
    override fun removeById(id: Long): Boolean = bookRepository.removeById(id) > 0

}
