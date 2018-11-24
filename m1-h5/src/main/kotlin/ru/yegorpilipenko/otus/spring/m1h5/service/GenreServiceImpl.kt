package ru.yegorpilipenko.otus.spring.m1h5.service

import org.springframework.stereotype.Service
import ru.yegorpilipenko.otus.spring.m1h5.dao.GenreDao
import ru.yegorpilipenko.otus.spring.m1h5.model.Genre

@Service
class GenreServiceImpl(val genreDao: GenreDao): GenreService {

    override fun findAll(): Collection<Genre> = genreDao.findAll()

    override fun findById(id: Long): Genre? = genreDao.findById(id)

    override fun save(genre: Genre): Genre = genreDao.save(genre)

    override fun removeById(id: Long): Boolean = genreDao.removeById(id)

}
