package com.apps.nosacikal.removie.ViewHolder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.nosacikal.removie.R;
import com.codewaves.youtubethumbnailview.ThumbnailLoadingListener;
import com.codewaves.youtubethumbnailview.ThumbnailView;

/*
 * Rabu 19 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieVideoViewHolder extends RecyclerView.ViewHolder {

    public ThumbnailView thumbnailView;
    public TextView videoTitle;

    public MovieVideoViewHolder(@NonNull View itemView) {
        super(itemView);

        thumbnailView = itemView.findViewById(R.id.video_image_view);
        videoTitle = itemView.findViewById(R.id.video_title);
    }


    public void setThumbnailView(final Activity activity, final String videoUrl) {
        thumbnailView.loadThumbnail(videoUrl, new ThumbnailLoadingListener() {
            @Override
            public void onLoadingStarted(@NonNull String url, @NonNull View view) {

            }

            @Override
            public void onLoadingComplete(@NonNull String url, @NonNull View view) {

            }

            @Override
            public void onLoadingCanceled(@NonNull String url, @NonNull View view) {

            }

            @Override
            public void onLoadingFailed(@NonNull String url, @NonNull View view, Throwable error) {
                Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
