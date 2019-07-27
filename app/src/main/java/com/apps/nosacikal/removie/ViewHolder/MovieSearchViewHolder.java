package com.apps.nosacikal.removie.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.apps.nosacikal.removie.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.squareup.picasso.Picasso;

/*
 * Sabtu 22 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieSearchViewHolder extends RecyclerView.ViewHolder {

    private KenBurnsView searchImageResult;
    public TextView searchTitleResult;
    public TextView searchVoteAverage;

    public MovieSearchViewHolder(@NonNull View itemView) {
        super(itemView);

        searchImageResult = itemView.findViewById(R.id.search_image_result);
        searchTitleResult = itemView.findViewById(R.id.search_title_result);
        searchVoteAverage = itemView.findViewById(R.id.search_vote_average);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        searchImageResult.setTransitionGenerator(generator);
    }

    public void setPosterImageView(Context context, String posterUrl) {
        Picasso.with(context).load(posterUrl).into(searchImageResult);
    }
}
