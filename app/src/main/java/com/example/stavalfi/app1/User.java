package com.example.stavalfi.app1;


import android.graphics.Bitmap;
import android.media.Image;

public class User {

    public final String userId;
    public final String username;
    public final String password;
    public final String emailAddress;
    public final String firstName;
    public final String lastName;
    public final String cityName;
    public final String streetAddress;
    public final Bitmap image;

    public User(String userId, String username, String password,
                String emailAddress, String firstName,
                String lastName, String cityName,
                String streetAddress, Bitmap image) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.image = image;
    }

    public User(String username, String password,
                String emailAddress, String firstName,
                String lastName, String cityName,
                String streetAddress, Bitmap image) {
        this("-1", username, password, emailAddress, firstName, lastName, cityName, streetAddress, image);
    }

    public boolean isFakeUser() {
        return this.userId == "-1";
    }
}
