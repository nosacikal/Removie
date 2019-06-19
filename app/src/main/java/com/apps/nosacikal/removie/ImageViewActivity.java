package com.apps.nosacikal.removie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ZoomageView zoomageView = findViewById(R.id.zoom_image_view);

        // set activity menjadi fullscreen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // ambil data dari PosterImageAdapter
        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {

            if (intent.getExtras().getString("image_url") != null) {

                String url = intent.getExtras().getString("image_url");
                Picasso.with(this).load(url).into(zoomageView);
            }
        }

    }
}
