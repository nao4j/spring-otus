package ru.yegorpilipenko.otus.spring.m1h5.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.yegorpilipenko.otus.spring.m1h5.model.Author
import java.sql.ResultSet

@Component
class AuthorMapper : RowMapper<Author> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Author?
            = Author(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))

}
