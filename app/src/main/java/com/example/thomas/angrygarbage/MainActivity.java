package com.example.thomas.angrygarbage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Game.GameViewer;

public class MainActivity extends AppCompatActivity {

    private GameViewer gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameView = new GameViewer(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.initFullscreen();
    }
}
