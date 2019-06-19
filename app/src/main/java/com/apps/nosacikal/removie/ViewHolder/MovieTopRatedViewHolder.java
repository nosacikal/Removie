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

public class MovieTopRatedViewHolder extends RecyclerView.ViewHolder {

    public KenBurnsView topRatedImage;
    public TextView topRatedTitle;

    public MovieTopRatedViewHolder(@NonNull View itemView) {
        super(itemView);

        topRatedImage = itemView.findViewById(R.id.toprated_image);
        topRatedTitle = itemView.findViewById(R.id.toprated_title);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        topRatedImage.setTransitionGenerator(generator);
    }

    public void setPopularImage(Context context, String posterUrl) {
        Picasso.with(context).load(posterUrl).into(topRatedImage);
    }
}
