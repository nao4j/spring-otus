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
import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment
import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
@ComponentScan("ru.yegorpilipenko.otus.spring.m1h6.dao")
class BookCommentDaoTest: WithAssertions {

    companion object {
        val comment1 = BookComment(1, 1, "example@gmail.com", "Отличная книга!")
    }

    @Autowired
    lateinit var entityManager: TestEntityManager
    @Autowired
    lateinit var bookCommentDao: BookCommentDao

    @Test
    fun shouldPassForFindByIdIfExists() {
        assertThat(bookCommentDao.findById(1)).isEqualTo(comment1)
    }

    @Test
    fun shouldPassForFindByIdIfNotExists() {
        assertThat(bookCommentDao.findById(42)).isNull()
    }

    @Test
    fun shouldPassForSave() {
        val expected = BookComment(bookId = 1L, email = "example@gmail.com", text = "t")
        val actual = bookCommentDao.save(expected)
        assertThat(entityManager.find(BookComment::class.java, actual.id)).isEqualToIgnoringGivenFields(expected, "id")
    }

    @Test
    fun shouldTrueForDeleteByIdIfExists() {
        val expected = entityManager.find(Genre::class.java, comment1.id)
        assertThat(bookCommentDao.deleteById(expected.id!!)).isTrue()
    }

    @Test
    fun shouldFalseForDeleteByIdIfNotExists() {
        assertThat(bookCommentDao.deleteById(42)).isFalse()
    }

}
