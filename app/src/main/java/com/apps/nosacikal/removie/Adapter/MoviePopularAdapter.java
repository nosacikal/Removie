package com.apps.nosacikal.removie.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.nosacikal.removie.Models.MovieModelResult;
import com.apps.nosacikal.removie.MovieDetailActivity;
import com.apps.nosacikal.removie.R;
import com.apps.nosacikal.removie.ViewHolder.MoviePopularViewHolder;

import java.util.List;

/*
 * Kamis 13 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularViewHolder> {

    private Activity activity;
    private List<MovieModelResult> movieModelResultList;

    public MoviePopularAdapter(Activity activity, List<MovieModelResult> movieModelResultList) {
        this.activity = activity;
        this.movieModelResultList = movieModelResultList;
    }

    @NonNull
    @Override
    public MoviePopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_popular, viewGroup, false);

        return new MoviePopularViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MoviePopularViewHolder moviePopularViewHolder, int i) {
        final MovieModelResult movieModelResult = movieModelResultList.get(i);

        moviePopularViewHolder.setPopularImage(activity, movieModelResult.getPoster_path());

        String title = movieModelResult.getTitle();

        if (title.length() > 20) {
            moviePopularViewHolder.popularTitle.setText(title.substring(0, 22) + "...");
        } else {
            moviePopularViewHolder.popularTitle.setText(title);
        }

        moviePopularViewHolder.voteAverage.setText(Double.toString(movieModelResult.getVote_average()) + " / 10");

        // on click ke detail movie
        moviePopularViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MovieDetailActivity.class);
                intent.putExtra("id", String.valueOf(movieModelResult.getId()));
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelResultList.size();
    }
}
