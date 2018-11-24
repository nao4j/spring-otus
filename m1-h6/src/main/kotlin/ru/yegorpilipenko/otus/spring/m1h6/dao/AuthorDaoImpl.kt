package ru.yegorpilipenko.otus.spring.m1h6.dao

import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h6.entity.Author
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class AuthorDaoImpl(@PersistenceContext val entityManager: EntityManager) : AuthorDao {

    override fun findAll(): Collection<Author> = entityManager.createQuery(
            "SELECT a FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Author a",
            Author::class.java
    ).resultList

    override fun findAll(ids: Collection<Long>): Collection<Author> = entityManager.createQuery(
            "SELECT a FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Author a WHERE a.id in :ids",
            Author::class.java
    ).setParameter("ids", ids).resultList

    override fun findById(id: Long): Author? = entityManager.createQuery(
            "SELECT a FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Author a WHERE a.id = :id",
            Author::class.java
    ).setParameter("id", id).resultList.firstOrNull()

    override fun save(author: Author): Author {
        entityManager.persist(author)
        return author
    }

    override fun deleteById(id: Long): Boolean = entityManager.createQuery(
            "DELETE FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Author a WHERE a.id = :id"
    ).setParameter("id", id).executeUpdate() > 0

}
