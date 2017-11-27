package com.example.stavalfi.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final EditText username = (EditText) findViewById(R.id.username_editText);
        final EditText password = (EditText) findViewById(R.id.password_editText);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserManager.login(username.getText().toString(), password.getText().toString())) {

                }
            }
        });

        Button register = (Button) findViewById(R.id.go_register_page_button);
        final LogInActivity me = this;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
}
