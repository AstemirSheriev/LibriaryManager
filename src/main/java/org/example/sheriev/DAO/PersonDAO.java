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
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person order by id", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("Select * from Person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().
                filter( person -> person.getId() == id).
                findFirst().
                orElse(null);
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON (name, surname, birthdate) values (?, ?, ?)", person.getName(), person.getSurname(), person.getBirthdate());
    }

    public void edit(Person person, int id) {
        jdbcTemplate.update("Update Person set name=?, surname=?, birthdate=? where id=?",
                person.getName(), person.getSurname(), person.getBirthdate(), id);
    }

    public Person getNameByBook(int id) {
        return jdbcTemplate.query("Select p.id, p.name, p.surname, p.birthdate from Book b join Person p on b.person_id=p.id where b.book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().
                findFirst().
                orElse(null);
    }

    public List<Book> checkBooks(int id) {
        return jdbcTemplate.query("Select Book.* from Person join Book on book.person_id=person.id where person.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE id = ?", id);
    }
}
