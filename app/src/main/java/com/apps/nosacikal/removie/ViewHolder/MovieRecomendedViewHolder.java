package com.apps.nosacikal.removie.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.nosacikal.removie.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.squareup.picasso.Picasso;

/*
 * Senin 1 Juli 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieRecomendedViewHolder extends RecyclerView.ViewHolder {

    public ImageView recomendedImage;
    public TextView recomendedTitle;
    public TextView recomendedVoteAverage;

    public MovieRecomendedViewHolder(@NonNull View itemView) {
        super(itemView);

        recomendedImage = itemView.findViewById(R.id.recomended_image);
        recomendedTitle = itemView.findViewById(R.id.recomended_title);
        recomendedVoteAverage = itemView.findViewById(R.id.recomended_vote_average);

//        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
//        recomendedImage.setTransitionGenerator(generator);
    }

    public void setRecomendedImage(Context context, String posterUrl) {
        Picasso.with(context).load(posterUrl).into(recomendedImage);
    }
}
