package com.example.stavalfi.app1;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final EditText username = (EditText) findViewById(R.id.username_editText);
        final EditText password = (EditText) findViewById(R.id.password_editText);

        final TextView showErrorInLoggingIn = (TextView) findViewById(R.id.show_error_in_log_in);

        final LogInActivity me = this;

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserManager.getInstance().login(username.getText().toString(), password.getText().toString())) {
                    Intent intent = new Intent(me, MenuActivity.class);
                    startActivity(intent);
                } else {
                    showErrorInLoggingIn.setText(getApplicationContext().getResources().getString(R.string.wrong_username_or_password));
                }
            }
        });

        Button register = (Button) findViewById(R.id.go_register_page_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
}
