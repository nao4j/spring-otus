package ru.yegorpilipenko.otus.spring.m1h6.dao

import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h6.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h6.entity.Book
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class BookDaoImpl(@PersistenceContext val entityManager: EntityManager): BookDao {

    override fun findAll(): Collection<ShortBook> {
        val books = entityManager.createQuery(
                "SELECT b FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Book b",
                Book::class.java
        ).resultList
        return if (!books.isEmpty()) {
            books.map { book -> ShortBook(book.id, book.name) }
        } else {
            emptyList()
        }
    }

    override fun findById(id: Long): Book? = entityManager.createQuery(
            "SELECT b FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Book b WHERE b.id = :id",
            Book::class.java
    ).setParameter("id", id).resultList.firstOrNull()

    override fun save(book: Book): Book {
        entityManager.persist(book)
        return book
    }

    override fun deleteById(id: Long): Boolean = entityManager.createQuery(
            "DELETE FROM ru.yegorpilipenko.otus.spring.m1h6.entity.Book b WHERE b.id = :id"
    ).setParameter("id", id).executeUpdate() > 0

}
