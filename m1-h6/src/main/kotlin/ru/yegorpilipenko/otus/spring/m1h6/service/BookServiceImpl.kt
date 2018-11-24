package ru.yegorpilipenko.otus.spring.m1h6.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Propagation.SUPPORTS
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m1h6.dao.AuthorDao
import ru.yegorpilipenko.otus.spring.m1h6.dao.BookDao
import ru.yegorpilipenko.otus.spring.m1h6.dao.GenreDao
import ru.yegorpilipenko.otus.spring.m1h6.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h6.entity.Book

@Service
class BookServiceImpl(val bookDao: BookDao, val authorDao: AuthorDao, val genreDao: GenreDao): BookService {

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findAll(): Collection<ShortBook> = bookDao.findAll()

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findById(id: Long): Book? = bookDao.findById(id)

    @Transactional(propagation = REQUIRED)
    override fun save(book: ShortBook, authorIds: Collection<Long>, genreIds: Collection<Long>): Book {
        val authors = authorDao.findAll(authorIds)
        val genres = genreDao.findAll(genreIds)
        return bookDao.save(Book(book.id, book.name, authors, genres, emptyList()))
    }

    @Transactional(propagation = REQUIRED)
    override fun removeById(id: Long): Boolean = bookDao.deleteById(id)

}
