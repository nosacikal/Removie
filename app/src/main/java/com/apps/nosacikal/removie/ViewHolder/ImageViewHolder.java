package com.apps.nosacikal.removie.ViewHolder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.apps.nosacikal.removie.R;
import com.squareup.picasso.Picasso;


/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class ImageViewHolder extends RecyclerView.ViewHolder {

    public ImageView profileImages;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        profileImages = itemView.findViewById(R.id.profile_images);
    }

    public void setProfileImage(Activity activity, String profilePath) {

        Picasso.with(activity).load(profilePath).into(profileImages);
    }
}
