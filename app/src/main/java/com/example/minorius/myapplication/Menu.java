package com.example.minorius.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by minorius on 29.12.2016.
 */

public class Menu extends AppCompatActivity {

    private Button listening_id;
    private Button quiz_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_la);

        listening_id = (Button) findViewById(R.id.listening_id);
        quiz_id = (Button) findViewById(R.id.quiz_id);

        quiz_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Quiz.class);
                startActivity(i);
            }
        });

        listening_id = (Button) findViewById(R.id.listening_id);
        listening_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Tab.class);
                startActivity(i);
            }
        });
    }
}
