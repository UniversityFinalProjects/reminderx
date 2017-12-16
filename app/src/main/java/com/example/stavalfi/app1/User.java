package com.example.stavalfi.app1;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class User {

    private String username;
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String cityName;
    private String streetAddress;
    private String bitmapAsString;
    private String userRoleTypeId;

    public User(String username,
                String password,
                String emailAddress,
                String firstName,
                String lastName,
                String cityName,
                String streetAddress,
                String bitmapAsString,
                String userRoleTypeId) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.bitmapAsString = bitmapAsString;
        this.userRoleTypeId = userRoleTypeId;
    }

    public User() {
    }

    public static String bitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
        byte[] b = ByteStream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getBitmapAsString() {
        return bitmapAsString;
    }

    public void setBitmapAsString(String bitmapAsString) {
        this.bitmapAsString = bitmapAsString;
    }

    public String getUserRoleTypeId() {
        return userRoleTypeId;
    }

    public void setUserRoleTypeId(String userRoleTypeId) {
        this.userRoleTypeId = userRoleTypeId;
    }
}
