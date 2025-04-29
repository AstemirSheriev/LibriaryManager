package org.example.sheriev.DAO;

import org.example.sheriev.models.Book;
import org.example.sheriev.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book order by book_id", new BookMapper());
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, year) values (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("Select * from Book where book_id=?", new Object[]{id}, new BookMapper()).
                stream().
                filter(book -> book.getBookId() == id).
                findFirst().
                orElse(null);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("Select Person.* from Book join Person on book.person_id=person.id where book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().
                findAny();
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE Book set name=?, author=?, year=? where book_id=?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE BOOK SET person_id=? where book_id=?", person.getId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE BOOK SET person_id=null where book_id=?", id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BOOK where book_id=?", id);
    }
}
