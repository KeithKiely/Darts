package com.example.xdog.dartsscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
/**
 * Created by Keith Kiely on 22/08/2016.
 * Menu class: Splash screen with choice type
 */
public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameSetting.class);
        startActivity(intent);
    }

    public void openPracticeSession(View view) {
        Intent intent = new Intent(this, PracticeSession.class);
        startActivity(intent);
    }
}
