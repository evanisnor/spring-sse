package com.evanisnor.api.model;

import java.util.Objects;

public class Person {

    private String id;
    private String name;

    public Person() {

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
