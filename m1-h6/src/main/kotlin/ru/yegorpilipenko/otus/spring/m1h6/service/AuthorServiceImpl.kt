package ru.yegorpilipenko.otus.spring.m1h6.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Propagation.SUPPORTS
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m1h6.dao.AuthorDao
import ru.yegorpilipenko.otus.spring.m1h6.entity.Author

@Service
class AuthorServiceImpl(val authorDao: AuthorDao): AuthorService {

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findAll(): Collection<Author> = authorDao.findAll()

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findById(id: Long): Author? = authorDao.findById(id)

    @Transactional(propagation = REQUIRED)
    override fun save(author: Author): Author = authorDao.save(author)

    @Transactional(propagation = REQUIRED)
    override fun removeById(id: Long): Boolean = authorDao.deleteById(id)

}
