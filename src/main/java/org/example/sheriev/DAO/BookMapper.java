package org.example.sheriev.DAO;

import org.example.sheriev.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setPersonId(resultSet.getInt("person_id"));
        book.setYear(resultSet.getInt("year"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        return book;
    }
}
