package ru.yegorpilipenko.otus.spring.m1h5.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m1h5.dao.AuthorDao
import ru.yegorpilipenko.otus.spring.m1h5.model.Author

@Service
class AuthorServiceImpl(val authorDao: AuthorDao): AuthorService {

    override fun findAll(): Collection<Author> = authorDao.findAll()

    override fun findById(id: Long): Author? = authorDao.findById(id)

    override fun save(author: Author): Author = authorDao.save(author)

    override fun removeById(id: Long): Boolean = authorDao.removeById(id)

}
