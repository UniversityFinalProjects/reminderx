package com.example.stavalfi.app1;


public class User {

    public final String personId;
    public final String username;
    public final String password;
    public final String emailAddress;
    public final String firstName;
    public final String lastName;
    public final String cityName;
    public final String streetAddress;
    public final String imageUrl;

    public User(String personId, String username, String password, String emailAddress, String firstName, String lastName, String cityName, String streetAddress, String imageUrl) {
        this.personId = personId;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.imageUrl = imageUrl;
    }
    public User(String username, String password, String emailAddress, String firstName, String lastName, String cityName, String streetAddress, String imageUrl) {
        this.personId = "user not registered - fake id";
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.imageUrl = imageUrl;
    }
}
