package ru.yegorpilipenko.otus.spring.m1h5.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import ru.yegorpilipenko.otus.spring.m1h5.dto.ShortBook
import ru.yegorpilipenko.otus.spring.m1h5.model.Book

@Repository
class BookPgDao(
        val jdbcTemplate: NamedParameterJdbcTemplate,
        val shortBookMapper: RowMapper<ShortBook>,
        val authorDao: AuthorDao,
        val genreDao: GenreDao
): BookDao {

    override fun findAll(): Collection<ShortBook>
            = jdbcTemplate.query("SELECT id, name FROM otus_library.books", shortBookMapper)

    override fun findById(id: Long): Book? {
        val book = jdbcTemplate.query(
                "SELECT id, name FROM otus_library.books WHERE id = :id",
                MapSqlParameterSource("id", id),
                shortBookMapper
        ).firstOrNull() ?: return null
        val authors = authorDao.findByBookId(book.id)
        val genres = genreDao.findByBookId(book.id)
        return Book(book.id, book.name, authors, genres)
    }

    override fun save(book: Book): Book {
        val bookParameterSource = MapSqlParameterSource("name", book.name)
        jdbcTemplate.update("INSERT INTO otus_library.books(name) VALUES (:name)", bookParameterSource)
        val bookId: Long = jdbcTemplate.queryForObject(
                "SELECT id FROM otus_library.books WHERE name = :name ORDER BY id DESC LIMIT 1",
                bookParameterSource,
                Long::class.java
        )!!
        book.authors.forEach { author ->
            val parameters = MapSqlParameterSource()
            parameters.addValue("bookId", bookId)
            parameters.addValue("authorId", author.id)
            jdbcTemplate.update(
                    "INSERT INTO otus_library.books_authors(book_id, author_id) VALUES (:bookId, :authorId)",
                    parameters
            )
        }
        book.genres.forEach { genre ->
            val parameters = MapSqlParameterSource()
            parameters.addValue("bookId", bookId)
            parameters.addValue("genreId", genre.id)
            jdbcTemplate.update(
                    "INSERT INTO otus_library.books_genres(book_id, genre_id) VALUES (:bookId, :genreId)",
                    parameters
            )
        }
        return book.copy(id = bookId)
    }

    override fun removeById(id: Long): Boolean = jdbcTemplate.update(
            "DELETE FROM otus_library.books WHERE id = :id",
            MapSqlParameterSource("id", id)
    ) > 0

}
