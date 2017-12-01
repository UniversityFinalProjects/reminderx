package com.example.stavalfi.app1;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    Bitmap image = null;

    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText username = (EditText) findViewById(R.id.enter_username_editText);
        final EditText password = (EditText) findViewById(R.id.enter_password_editText);
        final EditText emailAddress = (EditText) findViewById(R.id.enter_email_editText);
        final EditText firstName = (EditText) findViewById(R.id.enter_first_name_editText);
        final EditText lastName = (EditText) findViewById(R.id.enter_last_name_editText);
        final EditText cityName = (EditText) findViewById(R.id.enter_city_name_editText);
        final EditText streetAddress = (EditText) findViewById(R.id.enter_street_address_editText);

        final TextView showErrorInRegistration = (TextView) findViewById(R.id.show_error_in_registration);


        Button registerButton = (Button) findViewById(R.id.register_button);
        final RegistrationActivity me = this;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        username.getText().toString(),
                        password.getText().toString(),
                        emailAddress.getText().toString(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        cityName.getText().toString(),
                        streetAddress.getText().toString(),
                        image);

                if (username.getText().toString().length() < 1 || password.getText().toString().length() < 1 ||
                        image == null) {
                    showErrorInRegistration.setText(getApplicationContext().getResources().getString(R.string.empty_fields));
                } else {
                    User newUser = UserManager.getInstance().register(user);

                    if (!newUser.isFakeUser()) {
                        // use successfully registered with good Id.
                        startActivity(new Intent(me, MenuActivity.class));
                    } else {
                        // user name exist
                        showErrorInRegistration.setText(getApplicationContext().getResources().getString(R.string.username_exist));
                    }
                }
            }
        });

        Button pickImage = (Button) findViewById(R.id.get_image);
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            Uri chosenImageUri = data.getData();

            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
