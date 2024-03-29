package com.apps.nosacikal.removie;

import android.content.Intent;
import android.os.Bundle;

import com.apps.nosacikal.removie.Utils.SharePref;
import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.cuneytayyildiz.onboarder.utils.OnboarderPageChangeListener;

import java.util.Arrays;
import java.util.List;

/*
 * Minggu 30 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class Walkthrough extends OnboarderActivity implements OnboarderPageChangeListener {

    private SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // mengecek lauch activity - sebelum memanggil setContentView()
        sharePref = new SharePref(this);
        if (!sharePref.isFirstTimeLaunch()) {

            sharePref.setFirstTimeLaunch(false);
            startActivity(new Intent(Walkthrough.this, BottomNavigationActivity.class));
            finish();
        }

        List<OnboarderPage> pages = Arrays.asList(
                new OnboarderPage.Builder()
                        .title("Popular Movie")
                        .description("You can find movies that are currently popular")
                        .imageResourceId(R.drawable.popular)
                        .backgroundColor(R.color.background)
                        .titleColor(R.color.white)
                        .descriptionColor(R.color.white)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("Top Rated Movie")
                        .description("You can see movies that are top rated")
                        .imageResourceId(R.drawable.toprated)
                        .backgroundColor(R.color.background)
                        .titleColor(R.color.white)
                        .descriptionColor(R.color.white)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("Search Movie")
                        .description("You can search for movies that you want")
                        .imageResourceId(R.drawable.search_movie)
                        .backgroundColor(R.color.background)
                        .titleColor(R.color.white)
                        .descriptionColor(R.color.white)
                        .multilineDescriptionCentered(true)
                        .build()
        );

        setActiveIndicatorColor(android.R.color.white);
        setOnboarderPageChangeListener(this);
        initOnboardingPages(pages);
    }


    @Override
    public void onFinishButtonPressed() {
        // mengecek lauch activity - sebelum memanggil setContentView()
        sharePref = new SharePref(this);

        sharePref.setFirstTimeLaunch(false);
        startActivity(new Intent(Walkthrough.this, BottomNavigationActivity.class));
        finish();
    }

    @Override
    protected void onSkipButtonPressed() {
        super.onSkipButtonPressed();
    }

    @Override
    public void onPageChanged(int position) {

    }
}
