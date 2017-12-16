package com.example.stavalfi.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        final TextView userRoleType = (TextView) findViewById(R.id.user_role_type);

        FirebaseDatabase.getInstance()
                .getReference(DbTables.users)
                .child(UserManager.getInstance().getLoggedInUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User loggedInUser = dataSnapshot.getValue(User.class);
                        username.setText(getApplicationContext().getResources().getString(R.string.enter_username) + ": " + loggedInUser.getUsername());
                        if (loggedInUser.getBitmapAsString() != null)
                            image.setImageBitmap(User.stringToBitMap(loggedInUser.getBitmapAsString()));
                        emailAddress.setText(getApplicationContext().getResources().getString(R.string.enter_email) + ": " + loggedInUser.getEmailAddress());
                        firstName.setText(getApplicationContext().getResources().getString(R.string.enter_first_name) + ": " + loggedInUser.getFirstName());
                        lastName.setText(getApplicationContext().getResources().getString(R.string.enter_last_name) + ": " + loggedInUser.getLastName());
                        cityName.setText(getApplicationContext().getResources().getString(R.string.enter_city_name) + ": " + loggedInUser.getCityName());
                        streetAddress.setText(getApplicationContext().getResources().getString(R.string.enter_street_address) + ": " + loggedInUser.getStreetAddress());

                        FirebaseDatabase.getInstance()
                                .getReference(DbTables.userRoleTypes)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                                            if (messageSnapshot.getKey().equals(loggedInUser.getUserRoleTypeId())) {
                                                UserRoleType userRoleType = messageSnapshot.getValue(UserRoleType.class);
                                                streetAddress.setText(getApplicationContext().getResources().getString(R.string.user_role_type) + ": " + userRoleType.getRole());
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
