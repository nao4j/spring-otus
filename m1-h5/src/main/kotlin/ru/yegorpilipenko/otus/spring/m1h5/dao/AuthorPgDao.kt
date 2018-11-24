package ru.yegorpilipenko.otus.spring.m1h5.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h5.model.Author

@Repository
class AuthorPgDao(val jdbcTemplate: NamedParameterJdbcTemplate, val authorMapper: RowMapper<Author>) : AuthorDao {

    override fun findAll(): Collection<Author>
            = jdbcTemplate.query("SELECT id, first_name, last_name FROM otus_library.authors", authorMapper)

    override fun findAll(ids: Iterable<Long>): Collection<Author> = jdbcTemplate.query(
            "SELECT id, first_name, last_name FROM otus_library.authors WHERE id in (:ids)",
            MapSqlParameterSource("ids", ids),
            authorMapper
    )

    override fun findById(id: Long): Author? = jdbcTemplate.query(
            "SELECT id, first_name, last_name FROM otus_library.authors WHERE id = :id",
            MapSqlParameterSource("id", id),
            authorMapper
    ).firstOrNull()

    override fun findByBookId(bookId: Long): Collection<Author> = jdbcTemplate.query(
            """SELECT a.id, a.first_name, a.last_name
               FROM otus_library.books_authors ba JOIN otus_library.authors a ON ba.author_id = a.id
               WHERE ba.book_id = :bookId""",
            MapSqlParameterSource("bookId", bookId),
            authorMapper
    )

    override fun save(author: Author): Author {
        val namedParameters = BeanPropertySqlParameterSource(author)
        jdbcTemplate.update(
                "INSERT INTO otus_library.authors(first_name, last_name) VALUES (:firstName, :lastName)",
                namedParameters
        )
        val id = jdbcTemplate.queryForObject(
                """SELECT id FROM otus_library.authors WHERE first_name = :firstName AND last_name = :lastName
                   ORDER BY id DESC LIMIT 1""",
                namedParameters,
                Long::class.java
        )
        return author.copy(id = id!!.toLong())
    }

    override fun removeById(id: Long): Boolean = jdbcTemplate.update(
            "DELETE FROM otus_library.authors WHERE id = :id",
            MapSqlParameterSource("id", id)
    ) > 0

}
