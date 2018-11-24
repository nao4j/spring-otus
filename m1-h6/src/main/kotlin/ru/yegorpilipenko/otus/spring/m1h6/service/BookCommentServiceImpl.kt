package ru.yegorpilipenko.otus.spring.m1h6.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Propagation.SUPPORTS
import org.springframework.transaction.annotation.Transactional
import ru.yegorpilipenko.otus.spring.m1h6.dao.BookCommentDao
import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment

@Service
class BookCommentServiceImpl(val bookCommentDao: BookCommentDao): BookCommentService {

    @Transactional(propagation = SUPPORTS, readOnly = true)
    override fun findById(id: Long): BookComment? = bookCommentDao.findById(id)

    @Transactional(propagation = REQUIRED)
    override fun save(bookComment: BookComment): BookComment = bookCommentDao.save(bookComment)

    @Transactional(propagation = REQUIRED)
    override fun removeById(id: Long): Boolean = bookCommentDao.deleteById(id)
}
