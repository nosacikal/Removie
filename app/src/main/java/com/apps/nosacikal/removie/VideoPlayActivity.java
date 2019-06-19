package com.apps.nosacikal.removie;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.apps.nosacikal.removie.Adapter.ExtraVideoRecyclerAdapter;
import com.apps.nosacikal.removie.Models.MovieVideosResults;
import com.apps.nosacikal.removie.Utils.FullScreenHelper;
import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import java.util.ArrayList;
import java.util.Objects;

public class VideoPlayActivity extends AppCompatActivity {

    private ThumbnailView thumbnailView;
    private YouTubePlayerView playerView;
    private ProgressBar progressBar;
    private RecyclerView otherVideoRecyclerView;
    private FullScreenHelper fullScreenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        Intent intent = getIntent();

        ThumbnailLoader.initialize(BuildConfig.GOOGLE_CLOUD_API_KEY);

        fullScreenHelper = new FullScreenHelper(this);

        thumbnailView = findViewById(R.id.video_thumbnail_view);

        playerView = findViewById(R.id.player_view);

        AppCompatTextView videoTitle = findViewById(R.id.play_video_title);
        AppCompatTextView noResultsFound = findViewById(R.id.no_result_found);

        otherVideoRecyclerView = findViewById(R.id.others_video_recycler_view);
        otherVideoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        progressBar = findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(0XFFFFFFFF, PorterDuff.Mode.MULTIPLY);

        // ambil arraylist dan position
        if (intent != null && intent.getExtras() != null) {

            // mengambil data menggunakan parcelable
            ArrayList<MovieVideosResults> movieVideosResultsArrayList = intent.getExtras().getParcelableArrayList("video");

            int position = Integer.parseInt(Objects.requireNonNull(intent.getExtras().getString("position")));

            if (movieVideosResultsArrayList != null && movieVideosResultsArrayList.size() > 0) {

                final String videoId = movieVideosResultsArrayList.get(position).getKey();
                String title = movieVideosResultsArrayList.get(position).getName();

                if (title != null) {
                    videoTitle.setText(title);
                }

                if (videoId != null) {

                    String baseUrl = "https://www.youtube.com/watch?v=";

                    thumbnailView.loadThumbnail(baseUrl + videoId);

                    playerView.initialize(new YouTubePlayerInitListener() {
                        @Override
                        public void onInitSuccess(@NonNull final YouTubePlayer youTubePlayer) {
                            youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady() {
                                    // ketika video ready, lalu hide thumbnail
                                    thumbnailView.setVisibility(View.GONE);

                                    progressBar.setVisibility(View.GONE);

                                    // show youtube player
                                    playerView.setVisibility(View.VISIBLE);

                                    if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                                        youTubePlayer.loadVideo(videoId, 0);
                                    } else {
                                        youTubePlayer.cueVideo(videoId, 0);
                                    }
                                }
                            });

                            // mengatur layar video
                            playerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
                                @Override
                                public void onYouTubePlayerEnterFullScreen() {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                    fullScreenHelper.enterFullScreen();
                                }

                                @Override
                                public void onYouTubePlayerExitFullScreen() {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                    fullScreenHelper.exitFullScreen();
                                }
                            });
                        }
                    }, true);

                    // load video lain ke recycler view
                    ArrayList<MovieVideosResults> movieVideosResultsList = new ArrayList<>(movieVideosResultsArrayList);

                    // hapus video sekarang dari list
                    movieVideosResultsList.remove(position);

                    if (movieVideosResultsList.size() > 0) {
                        noResultsFound.setVisibility(View.GONE);

                        ExtraVideoRecyclerAdapter adapter = new ExtraVideoRecyclerAdapter(VideoPlayActivity.this, movieVideosResultsList);

                        otherVideoRecyclerView.setAdapter(adapter);

                        otherVideoRecyclerView.setVisibility(View.VISIBLE);

                        // set animation

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(VideoPlayActivity.this, R.anim.layout_slide_bottom);
                        otherVideoRecyclerView.setLayoutAnimation(controller);
                        otherVideoRecyclerView.scheduleLayoutAnimation();

                    } else {
                        otherVideoRecyclerView.setVisibility(View.GONE);
                        noResultsFound.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    // exit fullscreen dengan backpress

    @Override
    public void onBackPressed() {

        if (playerView.isFullScreen()) {
            playerView.exitFullScreen();
        } else {
            otherVideoRecyclerView.setVisibility(View.GONE);
            playerView.setVisibility(View.GONE);
            thumbnailView.setVisibility(View.VISIBLE);
            super.onBackPressed();
        }
    }
}
