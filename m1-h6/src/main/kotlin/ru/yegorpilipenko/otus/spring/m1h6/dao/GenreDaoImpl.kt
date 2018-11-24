package ru.yegorpilipenko.otus.spring.m1h6.dao

import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class GenreDaoImpl(@PersistenceContext val entityManager: EntityManager) : GenreDao {

    override fun findAll(): Collection<Genre> = entityManager.createQuery(
            "SELECT g FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Genre g",
            Genre::class.java
    ).resultList

    override fun findAll(ids: Collection<Long>): Collection<Genre> = entityManager.createQuery(
            "SELECT g FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Genre g WHERE g.id IN :ids",
            Genre::class.java
    ).setParameter("ids", ids).resultList

    override fun findById(id: Long): Genre? = entityManager.createQuery(
            "SELECT g FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Genre g WHERE g.id = :id",
            Genre::class.java
    ).setParameter("id", id).resultList.firstOrNull()

    override fun save(genre: Genre): Genre {
        entityManager.persist(genre)
        return genre
    }

    override fun deleteById(id: Long): Boolean = entityManager.createQuery(
            "DELETE FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Genre g WHERE g.id = :id"
    ).setParameter("id", id).executeUpdate() > 0

}
