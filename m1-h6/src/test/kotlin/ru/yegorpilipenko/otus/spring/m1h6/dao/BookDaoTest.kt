package ru.yegorpilipenko.otus.spring.m1h6.dao

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.yegorpilipenko.otus.spring.m1h6.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h6.entity.Author
import ru.yegorpilipenko.otus.spring.m1h6.entity.Book
import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment
import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
@ComponentScan("ru.yegorpilipenko.otus.spring.m1h6.dao")
class BookDaoTest: WithAssertions {

    companion object {
        val book1 = ShortBook(1, "Шаблоны корпоративных приложений")
        val fullBook1 = Book(
                1,
                "Шаблоны корпоративных приложений",
                listOf(Author(1, "Мартин", "Фаулер")),
                listOf(Genre(1, "Научно-техническая литература")),
                listOf(BookComment(1L, 1L, "example@gmail.com", "Отличная книга!"))
        )
        val book2 = ShortBook(2, "Архитектура корпоративных программных приложений")
        val book3 = ShortBook(3, "Рефакторинг. Улучшение существующего кода")
        val book4 = ShortBook(4, "Основы. Краткое руководство по унифицированному языку моделирования")
        val book5 = ShortBook(5, "Трудно быть богом")
    }

    @Autowired
    lateinit var entityManager: TestEntityManager
    @Autowired
    lateinit var bookDao: BookDao

    @Test
    fun shouldPassForFindAllIfNotEmpty() {
        assertThat(bookDao.findAll()).containsExactlyInAnyOrder(book1, book2, book3, book4, book5)
    }

    @Test
    fun shouldPassForFindAllIfEmpty() {
        clear()
        assertThat(bookDao.findAll()).isEmpty()
    }

    @Test
    fun shouldPassForFindByIdIfExists() {
        assertThat(bookDao.findById(fullBook1.id!!)).isEqualToComparingFieldByFieldRecursively(fullBook1)
    }

    @Test
    fun shouldPassForFindByIdIfNotExists() {
        assertThat(bookDao.findById(42)).isNull()
    }

    @Test
    fun shouldPassForSave() {
        val expected = Book(name = "t", authors = emptyList(), genres = emptyList(), comments = emptyList())
        val actual = bookDao.save(expected)
        assertThat(entityManager.find(Book::class.java, actual.id)).isEqualToIgnoringGivenFields(expected, "id")
    }

    @Test
    fun shouldTrueForDeleteByIdIfExists() {
        val expected = entityManager.find(Genre::class.java, book1.id)
        assertThat(bookDao.deleteById(expected.id!!)).isTrue()
    }

    @Test
    fun shouldFalseForDeleteByIdIfNotExists() {
        assertThat(bookDao.deleteById(42)).isFalse()
    }

    fun clear() {
        entityManager.remove(entityManager.find(Book::class.java, book1.id))
        entityManager.remove(entityManager.find(Book::class.java, book2.id))
        entityManager.remove(entityManager.find(Book::class.java, book3.id))
        entityManager.remove(entityManager.find(Book::class.java, book4.id))
        entityManager.remove(entityManager.find(Book::class.java, book5.id))
    }

}
