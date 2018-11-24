package ru.yegorpilipenko.otus.spring.m1h6.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m1h6.dao.GenreDao
import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre

@Service
class GenreServiceImpl(val genreDao: GenreDao): GenreService {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    override fun findAll(): Collection<Genre> = genreDao.findAll()

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    override fun findById(id: Long): Genre? = genreDao.findById(id)

    @Transactional(propagation = Propagation.REQUIRED)
    override fun save(genre: Genre): Genre = genreDao.save(genre)

    @Transactional(propagation = Propagation.REQUIRED)
    override fun removeById(id: Long): Boolean = genreDao.deleteById(id)

}
