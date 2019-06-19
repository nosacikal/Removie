package com.apps.nosacikal.removie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/*
 * Rabu 12 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class SplashscreenActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView title;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashscreenActivity.this, BottomNavigationActivity.class));
                finish();
            }
        }, 4000);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splashscreen_anim);
        logo.setAnimation(animation);
        title.setAnimation(animation);
        subtitle.setAnimation(animation);
    }
}
