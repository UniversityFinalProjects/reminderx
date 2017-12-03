package com.example.stavalfi.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        final TextView username = (TextView) findViewById(R.id.username_text_view);
        final ImageView image = (ImageView) findViewById(R.id.my_image_view);
        final TextView emailAddress = (TextView) findViewById(R.id.email_text_view);
        final TextView firstName = (TextView) findViewById(R.id.first_name_text_view);
        final TextView lastName = (TextView) findViewById(R.id.last_name_text_view);
        final TextView cityName = (TextView) findViewById(R.id.city_name_text_view);
        final TextView streetAddress = (TextView) findViewById(R.id.street_address_text_view);

        User loggedInUser = UserManager.getInstance().getLoggedInUser();
        username.setText(getApplicationContext().getResources().getString(R.string.enter_username) + ": " + loggedInUser.username);
        if (loggedInUser.image != null)
            image.setImageBitmap(UserManager.getInstance().getLoggedInUser().image);
        emailAddress.setText(getApplicationContext().getResources().getString(R.string.enter_email) + ": " + loggedInUser.emailAddress);
        firstName.setText(getApplicationContext().getResources().getString(R.string.enter_first_name) + ": " + loggedInUser.firstName);
        lastName.setText(getApplicationContext().getResources().getString(R.string.enter_last_name) + ": " + loggedInUser.lastName);
        cityName.setText(getApplicationContext().getResources().getString(R.string.enter_city_name) + ": " + loggedInUser.cityName);
        streetAddress.setText(getApplicationContext().getResources().getString(R.string.enter_street_address) + ": " + loggedInUser.streetAddress);
    }
}
