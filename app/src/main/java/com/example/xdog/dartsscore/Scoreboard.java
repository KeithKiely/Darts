package com.example.xdog.dartsscore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Scoreboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
    }

    public void openCalc(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent,1);
    }
}
