package com.example.stavalfi.app1;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LogInActivity extends AppCompatActivity {

    @Override
    public void onResume() {
        super.onResume();
        if (UserManager.getInstance().getLoggedInUserId() != null) {
            startActivity(new Intent(this, MenuActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        if (UserManager.getInstance().getLoggedInUserId() != null)
            return;

        final EditText username = (EditText) findViewById(R.id.username_editText);
        final EditText password = (EditText) findViewById(R.id.password_editText);

        final TextView showErrorInLoggingIn = (TextView) findViewById(R.id.show_error_in_log_in);

        final LogInActivity me = this;

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance()
                        .getReference(DbTables.users)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                                    User user1 = messageSnapshot.getValue(User.class);
                                    if (user1.getUsername().equals(username.getText().toString()) &&
                                            user1.getPassword().equals(password.getText().toString())) {
                                        UserManager.getInstance().setLoggedInUserId(messageSnapshot.getKey());
                                        startActivity(new Intent(me, MenuActivity.class));
                                        return;
                                    }
                                }
                                //  username or password incorrect
                                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.wrong_username_or_password), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
            }
        });
    }
}
