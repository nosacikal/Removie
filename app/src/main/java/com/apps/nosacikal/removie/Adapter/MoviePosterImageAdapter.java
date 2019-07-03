package com.apps.nosacikal.removie.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.nosacikal.removie.ImageViewActivity;
import com.apps.nosacikal.removie.Models.MovieImagesBackdropsAndPoster;
import com.apps.nosacikal.removie.R;
import com.apps.nosacikal.removie.ViewHolder.ImageViewHolder;

import java.util.List;
import java.util.Objects;

/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MoviePosterImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private Activity activity;
    private List<MovieImagesBackdropsAndPoster> movieImagesBackdropsAndPostersList;
    private final int limit = 10;

    public MoviePosterImageAdapter(Activity activity, List<MovieImagesBackdropsAndPoster> movieImagesBackdropsAndPostersList) {
        this.activity = activity;
        this.movieImagesBackdropsAndPostersList = movieImagesBackdropsAndPostersList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_poster_images, viewGroup, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder imageViewHolder, int i) {

        final MovieImagesBackdropsAndPoster movieImagesBackdropsAndPoster = movieImagesBackdropsAndPostersList.get(i);

        imageViewHolder.setProfileImage(activity, movieImagesBackdropsAndPoster.getFile_path());

        imageViewHolder.profileImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageViewIntent = new Intent(activity, ImageViewActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        imageViewHolder.profileImages, Objects.requireNonNull(ViewCompat.getTransitionName(imageViewHolder.profileImages)));
                imageViewIntent.putExtra("image_url", movieImagesBackdropsAndPoster.getFile_path());
                activity.startActivity(imageViewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieImagesBackdropsAndPostersList.size() > limit) {
            return limit;
        } else {
            return movieImagesBackdropsAndPostersList.size();
        }
    }
}
