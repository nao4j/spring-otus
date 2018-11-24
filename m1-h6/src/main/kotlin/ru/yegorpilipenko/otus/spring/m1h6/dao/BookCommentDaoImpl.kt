package ru.yegorpilipenko.otus.spring.m1h6.dao

import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment
import javax.persistence.EntityManager

@Repository
class BookCommentDaoImpl(val entityManager: EntityManager): BookCommentDao {

    override fun findById(id: Long): BookComment? = entityManager.createQuery(
            "SELECT c FROM ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment c WHERE c.id = :id",
            BookComment::class.java
    ).setParameter("id", id).resultList.firstOrNull()

    override fun save(bookComment: BookComment): BookComment {
        entityManager.persist(bookComment)
        return bookComment
    }

    override fun deleteById(id: Long): Boolean = entityManager.createQuery(
            "DELETE FROM ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment c WHERE c.id = :id"
    ).setParameter("id", id).executeUpdate() > 0

}
