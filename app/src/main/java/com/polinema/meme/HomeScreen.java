package com.polinema.meme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        button = (Button)findViewById(R.id.button_next);
        button2 = (Button)findViewById(R.id.button_meme);

        button.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent next = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(next);
        }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent meme = new Intent(HomeScreen.this, Main2Activity.class);

                startActivity(meme);
            }
        });

        }
}
