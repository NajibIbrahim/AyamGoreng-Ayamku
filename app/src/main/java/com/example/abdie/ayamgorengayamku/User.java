package com.example.abdie.ayamgorengayamku;


//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

    private String username,number,password,location;

    public User(String username, String number, String password, String location) {
        this.username = username;
        this.number = number;
        this.password = password;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
