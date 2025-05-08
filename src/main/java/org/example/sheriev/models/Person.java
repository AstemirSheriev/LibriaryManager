package org.example.sheriev.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Person {
    private Integer id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Surname cannot be empty")
    private String surname;
    @NotNull
    @Min(value = 1920, message = "Choose between 1920 and 2025")
    @Max(value = 2025, message = "Choose between 1920 and 2025")
    private int birthdate;

    public String getFullName(){
        return surname + " " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }

    public Person(int id, String name, String surname, int birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }
    public Person(){};
}
