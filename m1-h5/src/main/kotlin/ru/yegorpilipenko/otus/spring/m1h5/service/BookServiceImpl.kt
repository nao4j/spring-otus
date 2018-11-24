package ru.yegorpilipenko.otus.spring.m1h5.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m1h5.dao.AuthorDao
import ru.yegorpilipenko.otus.spring.m1h5.dao.BookDao
import ru.yegorpilipenko.otus.spring.m1h5.dao.GenreDao
import ru.yegorpilipenko.otus.spring.m1h5.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h5.model.Book

@Service
class BookServiceImpl(val bookDao: BookDao, val authorDao: AuthorDao, val genreDao: GenreDao): BookService {

    override fun findAll(): Collection<ShortBook> = bookDao.findAll()

    override fun findById(id: Long): Book? = bookDao.findById(id)

    override fun save(book: ShortBook, authorIds: Collection<Long>, genreIds: Collection<Long>): Book {
        val authors = authorDao.findAll(authorIds)
        val genres = genreDao.findAll(genreIds)
        return bookDao.save(Book(book.id, book.name, authors, genres))
    }

    override fun removeById(id: Long): Boolean = bookDao.removeById(id)

}
