package com.evanisnor.repository.model;

import java.util.Objects;

public class Person {

    private final String id;
    private final String name;

    public Person(String id) {
        this.id = id;
        this.name = "";
    }

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
