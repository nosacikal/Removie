package com.apps.nosacikal.removie.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.apps.nosacikal.removie.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.squareup.picasso.Picasso;

/*
 * Kamis 13 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MoviePopularViewHolder extends RecyclerView.ViewHolder {

    public KenBurnsView popularImage;
    public TextView popularTitle;
    public TextView voteAverage;

    public MoviePopularViewHolder(@NonNull View itemView) {
        super(itemView);

        popularImage = itemView.findViewById(R.id.popular_image);
        popularTitle = itemView.findViewById(R.id.popular_title);
        voteAverage = itemView.findViewById(R.id.popular_vote_average);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        popularImage.setTransitionGenerator(generator);
    }

    public void setPopularImage(Context context, String posterUrl) {
        Picasso.with(context).load(posterUrl).into(popularImage);
    }
}
