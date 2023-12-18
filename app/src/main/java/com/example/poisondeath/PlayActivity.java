package com.example.poisondeath;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {


    private MediaPlayer backgroundMusic;
    private MediaPlayer bottleSound;
    private ImageView poison;
    private Random random = new Random();
    private int lastDirection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        backgroundMusic = MediaPlayer.create(this, R.raw.musicafundo);
        bottleSound = MediaPlayer.create(this, R.raw.garrafa);


        backgroundMusic.setLooping(true);
        backgroundMusic.start();

        // Inicialize a ImageView corretamente usando findViewById
        poison = findViewById(R.id.poison);

        poison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newDirection = random.nextInt(1800);
                float eixoX = poison.getWidth() / 2;
                float eixoY = poison.getHeight() / 2;
                playBottleSound();

                Animation girar = new RotateAnimation(lastDirection, newDirection, eixoX, eixoY);
                girar.setDuration(2500);
                girar.setFillAfter(true);
                lastDirection = newDirection;
                poison.startAnimation(girar);
            }

        });
    }

    private void playBottleSound() {
        if (bottleSound != null) {
            bottleSound.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera os recursos dos MediaPlayers
        if (backgroundMusic != null) {
            backgroundMusic.release();
            backgroundMusic = null;
        }
        if (bottleSound != null) {
            bottleSound.release();
            bottleSound = null;
        }
    }
}