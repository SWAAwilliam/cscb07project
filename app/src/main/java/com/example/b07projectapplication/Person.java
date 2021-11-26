package com.example.b07projectapplication;

import java.util.Objects;

public class Person {
    String userUID;
    String firstName;
    String lastName;

    public Person(){
    }

    public Person(String userUID, String firstName, String lastName){
        this.userUID = userUID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUID);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if ( this.getClass() != obj.getClass() )
            return false;

        Person other = (Person) obj;
        return userUID.equals(other.userUID);
    }

}
