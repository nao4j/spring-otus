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
import ru.yegorpilipenko.otus.spring.m1h6.entity.Author

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
@ComponentScan("ru.yegorpilipenko.otus.spring.m1h6.dao")
class AuthorDaoTest: WithAssertions {

    companion object {
        val author1 = Author(1, "Мартин", "Фаулер")
        val author2 = Author(2, "Кендалл", "Скотт")
        val author3 = Author(3, "Аркадий", "Стругацкий")
        val author4 = Author(4, "Борис", "Стругацкий")
    }

    @Autowired
    lateinit var entityManager: TestEntityManager
    @Autowired
    lateinit var authorDao: AuthorDao

    @Test
    fun shouldPassForFindAllIfNotEmpty() {
        assertThat(authorDao.findAll()).containsExactlyInAnyOrder(author1, author2, author3, author4)
    }

    @Test
    fun shouldPassForFindAllIfEmpty() {
        clear()
        assertThat(authorDao.findAll()).isEmpty()
    }

    @Test
    fun shouldPassForFindAllByIdsIfIUseExistsIds() {
        assertThat(authorDao.findAll(listOf(2, 4))).containsExactlyInAnyOrder(author2, author4)
    }

    @Test
    fun shouldPassForFindAllByIdsIfIUseNotExistsIds() {
        assertThat(authorDao.findAll(listOf(10, 15))).isEmpty()
    }

    @Test
    fun shouldPassForFindByIdIfExists() {
        assertThat(authorDao.findById(1)).isEqualTo(author1)
    }

    @Test
    fun shouldPassForFindByIdIfNotExists() {
        assertThat(authorDao.findById(42)).isNull()
    }

    @Test
    fun shouldPassForSave() {
        val expected = Author(firstName = "t1", lastName = "t2")
        val actual = authorDao.save(expected)
        assertThat(entityManager.find(Author::class.java, actual.id)).isEqualToIgnoringGivenFields(expected, "id")
    }

    @Test
    fun shouldTrueForDeleteByIdIfExists() {
        assertThat(authorDao.deleteById(author1.id!!)).isTrue()
    }

    @Test
    fun shouldFalseForDeleteByIdIfNotExists() {
        assertThat(authorDao.deleteById(42)).isFalse()
    }

    fun clear() {
        entityManager.remove(entityManager.find(Author::class.java, author1.id))
        entityManager.remove(entityManager.find(Author::class.java, author2.id))
        entityManager.remove(entityManager.find(Author::class.java, author3.id))
        entityManager.remove(entityManager.find(Author::class.java, author4.id))
    }

}
