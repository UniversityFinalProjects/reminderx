package com.example.stavalfi.app1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;

public class MenuActivity extends AppCompatActivity {

    @Override
    public void onResume() {
        super.onResume();
        if (UserManager.getInstance().getLoggedInUserId() == null) {
            startActivity(new Intent(this, LogInActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (UserManager.getInstance().getLoggedInUserId() == null)
            return;


        final MenuActivity me = this;

        Button mapButton = findViewById(R.id.go_map_page_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, MapActivity.class);
                startActivity(intent);
            }
        });

        Button readmeButton = findViewById(R.id.go_readme_page_button);
        readmeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, ReadmeActivity.class);
                startActivity(intent);
            }
        });

        Button userInfoButton = findViewById(R.id.go_user_info_page_button);
        userInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, UserInfoActivity.class);
                startActivity(intent);
            }
        });

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager.getInstance().setLoggedInUserId(null);
                Intent intent = new Intent(me, LogInActivity.class);
                startActivity(intent);
            }
        });

        Button register = findViewById(R.id.go_register_page_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        // check the role type of the logged in user.
        // if the role type is not manager or admin, then
        // hide the button
        FirebaseDatabase.getInstance()
                .getReference(DbTables.users)
                .child(UserManager.getInstance().getLoggedInUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User loggedInUser = dataSnapshot.getValue(User.class);
                        FirebaseDatabase.getInstance()
                                .getReference(DbTables.userRoleTypes)
                                .child(loggedInUser.getUserRoleTypeId())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        UserRoleType userRoleType = dataSnapshot.getValue(UserRoleType.class);
                                        if (userRoleType.getRole().equals("admin"))
                                            register.setVisibility(View.VISIBLE);
                                        else
                                            register.setVisibility(View.GONE);
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
