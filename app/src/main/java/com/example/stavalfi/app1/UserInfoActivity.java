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

        final TextView username = findViewById(R.id.username_text_view);
        final ImageView image = findViewById(R.id.my_image_view);
        final TextView emailAddress = findViewById(R.id.email_text_view);
        final TextView firstName = findViewById(R.id.first_name_text_view);
        final TextView lastName = findViewById(R.id.last_name_text_view);
        final TextView cityName = findViewById(R.id.city_name_text_view);
        final TextView streetAddress = findViewById(R.id.street_address_text_view);
        final TextView userRoleType = findViewById(R.id.user_role_type);
        final TextView clockReminders = findViewById(R.id.clock_history_rich);
        final TextView roleHistory = findViewById(R.id.role_history_rich);

        // show user clock reminders
        FirebaseDatabase.getInstance()
                .getReference(DbTables.clockReminders)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            ClockReminder reminder = messageSnapshot.getValue(ClockReminder.class);
                            if (reminder.getUserId().equals(UserManager.getInstance().getLoggedInUserId()))
                                clockReminders.setText(clockReminders.getText() + "\n" + reminder.toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        // show user role history
        FirebaseDatabase.getInstance()
                .getReference(DbTables.userRoleTypeHistory)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            UserRoleTypeHistory role = messageSnapshot.getValue(UserRoleTypeHistory.class);
                            if (role.getUserId().equals(UserManager.getInstance().getLoggedInUserId())) {
                                // get role name
                                FirebaseDatabase.getInstance()
                                        .getReference(DbTables.userRoleTypes)
                                        .child(role.getUserRoleTypeId())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                UserRoleType userRole = dataSnapshot.getValue(UserRoleType.class);
                                                roleHistory.setText(roleHistory.getText() + "\n" + userRole.getRole());
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        // show user info + user role type
        FirebaseDatabase.getInstance()
                .getReference(DbTables.users)
                .child(UserManager.getInstance().getLoggedInUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User loggedInUser = dataSnapshot.getValue(User.class);
                        username.setText("hi " + loggedInUser.getUsername() + ", have a good day!");
                        if (loggedInUser.getBitmapAsString() != null)
                            image.setImageBitmap(User.stringToBitMap(loggedInUser.getBitmapAsString()));
                        emailAddress.setText(getApplicationContext().getResources().getString(R.string.enter_email) + ": " + loggedInUser.getEmailAddress());
                        firstName.setText(getApplicationContext().getResources().getString(R.string.enter_first_name) + ": " + loggedInUser.getFirstName());
                        lastName.setText(getApplicationContext().getResources().getString(R.string.enter_last_name) + ": " + loggedInUser.getLastName());
                        cityName.setText(getApplicationContext().getResources().getString(R.string.enter_city_name) + ": " + loggedInUser.getCityName());
                        streetAddress.setText(getApplicationContext().getResources().getString(R.string.enter_street_address) + ": " + loggedInUser.getStreetAddress());

                        // get role name
                        FirebaseDatabase.getInstance()
                                .getReference(DbTables.userRoleTypes)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                                            if (messageSnapshot.getKey().equals(loggedInUser.getUserRoleTypeId())) {
                                                UserRoleType userRole = messageSnapshot.getValue(UserRoleType.class);
                                                userRoleType.setText(getApplicationContext().getResources().getString(R.string.user_role_type) + ": " + userRole.getRole());
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
