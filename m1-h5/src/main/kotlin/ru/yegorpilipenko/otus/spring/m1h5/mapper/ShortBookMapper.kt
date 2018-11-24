package ru.yegorpilipenko.otus.spring.m1h5.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.yegorpilipenko.otus.spring.m1h5.dto.ShortBook
import java.sql.ResultSet

@Component
class ShortBookMapper : RowMapper<ShortBook> {

    override fun mapRow(rs: ResultSet, rowNum: Int): ShortBook? = ShortBook(rs.getLong("id"), rs.getString("name"))

}
