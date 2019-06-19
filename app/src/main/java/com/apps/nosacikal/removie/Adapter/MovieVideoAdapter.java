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

import com.apps.nosacikal.removie.Models.MovieVideosResults;
import com.apps.nosacikal.removie.R;
import com.apps.nosacikal.removie.VideoPlayActivity;
import com.apps.nosacikal.removie.ViewHolder.MovieVideoViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Rabu 19 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieVideoAdapter extends RecyclerView.Adapter<MovieVideoViewHolder> {

    private Activity activity;
    private List<MovieVideosResults> movieVideosResultsList;

    public MovieVideoAdapter(Activity activity, List<MovieVideosResults> movieVideosResultsList) {
        this.activity = activity;
        this.movieVideosResultsList = movieVideosResultsList;
    }

    @NonNull
    @Override
    public MovieVideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.video_layout, viewGroup, false);
        return new MovieVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVideoViewHolder movieVideoViewHolder, int i) {
        MovieVideosResults movieVideosResults = movieVideosResultsList.get(i);

        String baseUrl = "https://www.youtube.com/watch?v=";

        String videoUrl = baseUrl + movieVideosResults.getKey();

        movieVideoViewHolder.setThumbnailView(activity, videoUrl);
        movieVideoViewHolder.videoTitle.setText(movieVideosResults.getName());

        movieVideoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<MovieVideosResults> movieVideosResultsArrayList = new ArrayList<>(movieVideosResultsList);

                Intent intent = new Intent(activity, VideoPlayActivity.class);

                // set animation
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, movieVideoViewHolder.thumbnailView, ViewCompat.getTransitionName(movieVideoViewHolder.thumbnailView));

                // put the current video position and video list
                intent.putExtra("position", String.valueOf(movieVideoViewHolder.getAdapterPosition()));
                intent.putExtra("video", movieVideosResultsArrayList);

                activity.startActivity(intent, compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieVideosResultsList.size();
    }
}
