package ru.yegorpilipenko.otus.spring.m2h1.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m2h1.entity.Genre
import ru.yegorpilipenko.otus.spring.m2h1.repository.GenreRepository

@Service
class GenreServiceImpl(val genreRepository: GenreRepository) : GenreService {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    override fun findAll(): Collection<Genre> = genreRepository.findAll()

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    override fun findById(id: Long): Genre? = genreRepository.findById(id).orElse(null)

    @Transactional(propagation = Propagation.REQUIRED)
    override fun save(genre: Genre): Genre = genreRepository.save(genre)

    @Transactional(propagation = Propagation.REQUIRED)
    override fun removeById(id: Long): Boolean = genreRepository.removeById(id) > 0

}
