package ru.yegorpilipenko.otus.spring.m2h1.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Propagation.SUPPORTS
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m2h1.entity.Author
import ru.yegorpilipenko.otus.spring.m2h1.repository.AuthorRepository

@Service
class AuthorServiceImpl(val authorRepository: AuthorRepository) : AuthorService {

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findAll(): Collection<Author> = authorRepository.findAll()

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findById(id: Long): Author? = authorRepository.findById(id).orElse(null)

    @Transactional(propagation = REQUIRED)
    override fun save(author: Author): Author = authorRepository.save(author)

    @Transactional(propagation = REQUIRED)
    override fun removeById(id: Long): Boolean = authorRepository.removeById(id) > 0

}
