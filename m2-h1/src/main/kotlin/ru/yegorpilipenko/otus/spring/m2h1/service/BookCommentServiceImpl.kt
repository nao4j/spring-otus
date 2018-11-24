package ru.yegorpilipenko.otus.spring.m2h1.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Propagation.SUPPORTS
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m2h1.entity.BookComment
import ru.yegorpilipenko.otus.spring.m2h1.repository.BookCommentRepository

@Service
class BookCommentServiceImpl(val bookCommentRepository: BookCommentRepository) : BookCommentService {

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findById(id: Long): BookComment? = bookCommentRepository.findById(id).orElse(null)

    @Transactional(propagation = REQUIRED)
    override fun save(bookComment: BookComment): BookComment = bookCommentRepository.save(bookComment)

    @Transactional(propagation = REQUIRED)
    override fun removeById(id: Long): Boolean = bookCommentRepository.removeById(id) > 0
}
