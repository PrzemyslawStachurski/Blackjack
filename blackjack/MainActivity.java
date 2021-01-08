package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.rules);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRules();
            }
        });

        button1 = (Button) findViewById(R.id.play);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlay();
            }
        });

    }

    public void openRules() {
        Intent intent = new Intent(this, rules.class);
        startActivity(intent);
    }

    public void openPlay() {
        Intent intent = new Intent(this, play.class);
        startActivity(intent);
    }



}
