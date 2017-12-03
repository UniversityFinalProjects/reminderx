package com.example.stavalfi.app1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final MenuActivity me = this;

        Button mapButton = (Button) findViewById(R.id.go_map_page_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, MapActivity.class);
                startActivity(intent);
            }
        });

        Button readmeButton = (Button) findViewById(R.id.go_readme_page_button);
        readmeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, ReadmeActivity.class);
                startActivity(intent);
            }
        });

        Button userInfoButton = (Button) findViewById(R.id.go_user_info_page_button);
        userInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
