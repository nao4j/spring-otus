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
import ru.yegorpilipenko.otus.spring.m1h6.entity.Genre

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
@ComponentScan("ru.yegorpilipenko.otus.spring.m1h6.dao")
class GenreDaoTest: WithAssertions {

    companion object {
        val genre1 = Genre(1, "Научно-техническая литература")
        val genre2 = Genre(2, "Научная фантастика")
        val genre3 = Genre(3, "Драма")
    }

    @Autowired
    lateinit var entityManager: TestEntityManager
    @Autowired
    lateinit var genreDao: GenreDao

    @Test
    fun shouldPassForFindAllIfNotEmpty() {
        assertThat(genreDao.findAll()).containsExactlyInAnyOrder(genre1, genre2, genre3)
    }

    @Test
    fun shouldPassForFindAllIfEmpty() {
        clear()
        assertThat(genreDao.findAll()).isEmpty()
    }

    @Test
    fun shouldPassForFindAllByIdsIfIUseExistsIds() {
        assertThat(genreDao.findAll(listOf(2, 3))).containsExactlyInAnyOrder(genre2, genre3)
    }

    @Test
    fun shouldPassForFindAllByIdsIfIUseNotExistsIds() {
        assertThat(genreDao.findAll(listOf(10, 15))).isEmpty()
    }

    @Test
    fun shouldPassForFindByIdIfExists() {
        assertThat(genreDao.findById(1)).isEqualTo(genre1)
    }

    @Test
    fun shouldPassForFindByIdIfNotExists() {
        assertThat(genreDao.findById(42)).isNull()
    }

    @Test
    fun shouldPassForSave() {
        val expected = Genre(name = "t")
        val actual = genreDao.save(expected)
        assertThat(entityManager.find(Genre::class.java, actual.id)).isEqualToIgnoringGivenFields(expected, "id")
    }

    @Test
    fun shouldTrueForDeleteByIdIfExists() {
        val expected = entityManager.find(Genre::class.java, genre1.id)
        assertThat(genreDao.deleteById(expected.id!!)).isTrue()
    }

    @Test
    fun shouldFalseForDeleteByIdIfNotExists() {
        assertThat(genreDao.deleteById(42)).isFalse()
    }

    fun clear() {
        entityManager.remove(entityManager.find(Genre::class.java, genre1.id))
        entityManager.remove(entityManager.find(Genre::class.java, genre2.id))
        entityManager.remove(entityManager.find(Genre::class.java, genre3.id))
    }

}
