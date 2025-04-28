package org.example.sheriev.DAO;

import org.example.sheriev.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT name, author, year FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, year) values (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }
}
