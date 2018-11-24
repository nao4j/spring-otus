package ru.yegorpilipenko.otus.spring.m1h5.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.yegorpilipenko.otus.spring.m1h5.model.Genre
import java.sql.ResultSet

@Component
class GenreMapper : RowMapper<Genre> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Genre? = Genre(rs.getLong("id"), rs.getString("name"))

}
