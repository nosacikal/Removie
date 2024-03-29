package com.apps.nosacikal.removie.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.nosacikal.removie.Models.MovieModelResult;
import com.apps.nosacikal.removie.MovieDetailActivity;
import com.apps.nosacikal.removie.R;
import com.apps.nosacikal.removie.ViewHolder.MovieSearchViewHolder;

import java.util.List;

/*
 * Sabtu 22 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchViewHolder> {

    private Activity activity;
    private List<MovieModelResult> movieModelResultList;

    public MovieSearchAdapter(Activity activity, List<MovieModelResult> movieModelResultList) {
        this.activity = activity;
        this.movieModelResultList = movieModelResultList;
    }

    @NonNull
    @Override
    public MovieSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_search, viewGroup, false);

        return new MovieSearchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieSearchViewHolder movieSearchViewHolder, int i) {

        final MovieModelResult movieModelResult = movieModelResultList.get(i);

        movieSearchViewHolder.setPosterImageView(activity, movieModelResult.getPoster_path());

        movieSearchViewHolder.searchTitleResult.setText(movieModelResult.getTitle());

        movieSearchViewHolder.searchVoteAverage.setText(Double.toString(movieModelResult.getVote_average()) + " / 10");

        // on click ke detail movie
        movieSearchViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
