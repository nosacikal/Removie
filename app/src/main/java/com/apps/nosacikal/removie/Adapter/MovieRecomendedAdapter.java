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
import com.apps.nosacikal.removie.ViewHolder.MovieRecomendedViewHolder;

import java.util.List;

/*
 * Senin 1 Juli 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieRecomendedAdapter extends RecyclerView.Adapter<MovieRecomendedViewHolder> {

    private Activity activity;
    private List<MovieModelResult> movieModelResultList;

    public MovieRecomendedAdapter(Activity activity, List<MovieModelResult> movieModelResultList) {
        this.activity = activity;
        this.movieModelResultList = movieModelResultList;
    }

    @NonNull
    @Override
    public MovieRecomendedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_recomended, viewGroup, false);

        return new MovieRecomendedViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieRecomendedViewHolder movieRecomendedViewHolder, int i) {
        final MovieModelResult movieModelResult = movieModelResultList.get(i);

        movieRecomendedViewHolder.setRecomendedImage(activity, movieModelResult.getBackdrop_path());

        movieRecomendedViewHolder.recomendedTitle.setText(movieModelResult.getTitle());
        movieRecomendedViewHolder.recomendedVoteAverage.setText(Double.toString(movieModelResult.getVote_average()) + " / 10");

        // on click ke detail movie
        movieRecomendedViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return 1;
    }
}
