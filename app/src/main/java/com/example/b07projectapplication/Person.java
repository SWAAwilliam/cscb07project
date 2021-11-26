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

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUserUID(String userUID) { this.userUID = userUID; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getUserUID() { return this.userUID; }


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
