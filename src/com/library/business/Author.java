package com.library.business;

public class Author extends Person {

    private String credential;

    private String biography;

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
