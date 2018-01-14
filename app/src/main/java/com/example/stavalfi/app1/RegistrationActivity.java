package com.example.stavalfi.app1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    Bitmap image = null;

    public static final int PICK_IMAGE = 1;
    public static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText username = findViewById(R.id.enter_username_editText);
        final EditText password = findViewById(R.id.enter_password_editText);
        final EditText emailAddress = findViewById(R.id.enter_email_editText);
        final EditText firstName = findViewById(R.id.enter_first_name_editText);
        final EditText lastName = findViewById(R.id.enter_last_name_editText);
        final EditText cityName = findViewById(R.id.enter_city_name_editText);
        final EditText streetAddress = findViewById(R.id.enter_street_address_editText);

        Button registerButton = findViewById(R.id.register_button);
        final RegistrationActivity me = this;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() < 1 || password.getText().toString().length() < 1 ||
                        image == null) {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(
                        username.getText().toString(),
                        password.getText().toString(),
                        emailAddress.getText().toString(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        cityName.getText().toString(),
                        streetAddress.getText().toString(),
                        User.bitMapToString(image), "user");

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(DbTables.users);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            User user1 = messageSnapshot.getValue(User.class);
                            if (user1.getUsername().equals(user.getUsername())) {
                                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.username_exist), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        // register the user
                        UserManager.getInstance().registerUser(user);
                        // move to main menu
                        startActivity(new Intent(me, MenuActivity.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });

        Button pickImage = findViewById(R.id.get_image);
        pickImage.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        Button takePictureButton = findViewById(R.id.take_a_picture_button);
        takePictureButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case PICK_IMAGE: {
                Uri chosenImageUri = data.getData();

                // check image file extension

                String extension = getContentResolver().getType(chosenImageUri);
                extension = extension.substring(extension.lastIndexOf("/") + 1); // Without dot jpg, png

                if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")) {
                    // print: image added successfully
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.image_added_successfully), Toast.LENGTH_SHORT).show();
                } else {
                    // print: we do not accept any other extensions
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.wrong_extension) + ": " + extension, Toast.LENGTH_SHORT).show();
                    return;
                }

                // save selected image file

                try {
                    this.image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case REQUEST_IMAGE_CAPTURE: {
                Bundle extras = data.getExtras();
                this.image = (Bitmap) extras.get("data");
                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.image_added_successfully), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
