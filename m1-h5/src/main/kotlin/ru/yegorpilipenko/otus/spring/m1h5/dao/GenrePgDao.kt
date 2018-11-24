package ru.yegorpilipenko.otus.spring.m1h5.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h5.model.Genre

@Repository
class GenrePgDao(val jdbcTemplate: NamedParameterJdbcTemplate, val genreMapper: RowMapper<Genre>) : GenreDao {

    override fun findAll(): Collection<Genre>
            = jdbcTemplate.query("SELECT id, name FROM otus_library.genres", genreMapper)

    override fun findAll(ids: Iterable<Long>): Collection<Genre> = jdbcTemplate.query(
            "SELECT id, name FROM otus_library.genres WHERE id in (:ids)",
            MapSqlParameterSource("ids", ids),
            genreMapper
    )

    override fun findById(id: Long): Genre? = jdbcTemplate.query(
            "SELECT id, name FROM otus_library.genres WHERE id = :id",
            MapSqlParameterSource("id", id),
            genreMapper
    ).firstOrNull()

    override fun findByBookId(bookId: Long): Collection<Genre> = jdbcTemplate.query(
            """SELECT g.id, g.name
               FROM otus_library.books_genres bg JOIN otus_library.genres g ON bg.genre_id = g.id
               WHERE bg.book_id = :bookId""",
            MapSqlParameterSource("bookId", bookId),
            genreMapper
    )

    override fun save(genre: Genre): Genre {
        val namedParameters = BeanPropertySqlParameterSource(genre)
        jdbcTemplate.update("INSERT INTO otus_library.genres(name) VALUES (:name)", namedParameters)
        val id = jdbcTemplate.queryForObject(
                "SELECT id FROM otus_library.genres WHERE name = :name ORDER BY id DESC LIMIT 1",
                namedParameters,
                Long::class.java
        )
        return genre.copy(id = id!!.toLong())
    }

    override fun removeById(id: Long): Boolean
            = jdbcTemplate.update("DELETE FROM otus_library.genres WHERE id = :id", MapSqlParameterSource("id", id)) > 0

}
