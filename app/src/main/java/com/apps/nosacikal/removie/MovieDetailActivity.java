package com.apps.nosacikal.removie;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.nosacikal.removie.Adapter.MoviePosterImageAdapter;
import com.apps.nosacikal.removie.Adapter.MovieVideoAdapter;
import com.apps.nosacikal.removie.Client.RetrofitClient;
import com.apps.nosacikal.removie.Database.FavoriteDatabase;
import com.apps.nosacikal.removie.Database.FavoriteEntitas;
import com.apps.nosacikal.removie.Interfaces.RetrofitService;
import com.apps.nosacikal.removie.Models.MovieDetailModel;
import com.apps.nosacikal.removie.Models.MovieDetailGenres;
import com.apps.nosacikal.removie.Models.MovieImages;
import com.apps.nosacikal.removie.Models.MovieImagesBackdropsAndPoster;
import com.apps.nosacikal.removie.Models.MovieVideos;
import com.apps.nosacikal.removie.Models.MovieVideosResults;
import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Kamis 13 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 *
 * Change Log :
 * MEMBUAT DETAIL MOVIE
 * MENAMBAHKAN PHOTO GALLERY
 * MENAMBAHKAN PHOTO GALLERY DETAIL
 * MENYIMPAN FAVORITE MOVIE KE SQLite
 *
 *
 * */

public class MovieDetailActivity extends AppCompatActivity {

    private ProgressDialog progress;

    public static FavoriteDatabase db;
    private FavoriteEntitas favoriteEntitas;

//    List<FavoriteEntitas> favoriteEntitasList = new ArrayList<>();

    private ImageView movieDetailPoster;

    private LinearLayout movieDetailOriginalTitleLayout;
    private LinearLayout movieDetailRunningTimeLayout;
    private LinearLayout movieDetailReleaseLayout;
    private LinearLayout movieDetailLanguageLayout;

    private TextView movieDetailOriginalTitle;
    private TextView movieDetailRunningTime;
    private TextView movieDetailRelease;
    private TextView movieDetailLanguage;

    private ImageView movieDetailBackdrop;
    private TextView movieDetailTitle;
    private TextView movieDetailGenres;
    private TextView movieDetailReleaseDate;
    private TextView movieDetailVoteAverage;
    private TextView movieDetailOverview;

    private LinearLayout movieDetailVideoLayout;

    private RecyclerView movieDetailImagesRecyclerView;
    private RecyclerView movieDetailVideoRecyclerView;


    private ImageButton movieDetailFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();


        final Intent intent = getIntent();
        final RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
        ThumbnailLoader.initialize(BuildConfig.GOOGLE_CLOUD_API_KEY);


        db = Room.databaseBuilder(getApplicationContext(),
                FavoriteDatabase.class, "favorite")
                .allowMainThreadQueries().build();

        movieDetailPoster = findViewById(R.id.movie_detail_poster);

        movieDetailOriginalTitleLayout = findViewById(R.id.movie_detail_original_title_layout);
        movieDetailRunningTimeLayout = findViewById(R.id.movie_detail_running_layout);
        movieDetailReleaseLayout = findViewById(R.id.movie_detail_release_layout);
        movieDetailLanguageLayout = findViewById(R.id.movie_detail_language_layout);

        movieDetailOriginalTitle = findViewById(R.id.movie_detail_original_title);
        movieDetailRunningTime = findViewById(R.id.movie_detail_running);
        movieDetailRelease = findViewById(R.id.movie_detail_release);
        movieDetailLanguage = findViewById(R.id.movie_detail_language);


        movieDetailBackdrop = findViewById(R.id.movie_detail_backdrop);
        movieDetailTitle = findViewById(R.id.movie_detail_title);
        movieDetailGenres = findViewById(R.id.movie_detail_genres);
        movieDetailReleaseDate = findViewById(R.id.movie_detail_release_date);
        movieDetailVoteAverage = findViewById(R.id.movie_detail_vote_average);
        movieDetailOverview = findViewById(R.id.movie_detail_overview);

        movieDetailVideoLayout = findViewById(R.id.movie_detail_video_layout);

        movieDetailFavorite = findViewById(R.id.movie_detail_favorite);

        movieDetailImagesRecyclerView = findViewById(R.id.movie_detail_images_recycler_view);
        movieDetailImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieDetailVideoRecyclerView = findViewById(R.id.movie_detail_video_recycler_view);
        movieDetailVideoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        if (intent != null && intent.getExtras() != null) {
            if (intent.getExtras().getString("id") != null) {

                int id = Integer.parseInt(Objects.requireNonNull(intent.getExtras().getString("id")));

                // service untuk detail movie
                Call<MovieDetailModel> movieDetailCall = retrofitService.getMovieDetailById(id, BuildConfig.THE_MOVIE_DB_API_KEY);

                movieDetailCall.enqueue(new Callback<MovieDetailModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieDetailModel> call, @NonNull Response<MovieDetailModel> response) {
                        final MovieDetailModel movieDetailModelResponse = response.body();

                        progress.dismiss();

                        if (movieDetailModelResponse != null) {

                            prepareMovieDetail(movieDetailModelResponse);

                            if (db.favoriteDao().tampilFavoriteById(intent.getExtras().getString("id"))) {
                                movieDetailFavorite.setImageResource(R.drawable.ic_favorite_white);
                            }

                            movieDetailFavorite.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!db.favoriteDao().tampilFavoriteById(intent.getExtras().getString("id"))) {

                                        favoriteEntitas = new FavoriteEntitas();
                                        favoriteEntitas.setId(intent.getExtras().getString("id"));
                                        favoriteEntitas.setTitle(intent.getExtras().getString("title"));

                                        db.favoriteDao().tambahFavorite(favoriteEntitas);

                                        Log.d("TAMBAH", "TAMBAH DATA");
                                        Log.d("TAMBAH", "===========");

                                        Log.e("TAMBAH", "NAMA : " + movieDetailModelResponse.getId());
                                        Log.e("TAMBAH", "EMAIL : " + movieDetailModelResponse.getTitle());


                                        movieDetailFavorite.setImageResource(R.drawable.ic_favorite_white);

                                        Toast.makeText(MovieDetailActivity.this, "" + movieDetailModelResponse.getTitle() + " Was Added to Favorite", Toast.LENGTH_SHORT).show();
                                    } else {
                                        favoriteEntitas = new FavoriteEntitas();
                                        favoriteEntitas.setId(intent.getExtras().getString("id"));
                                        favoriteEntitas.setTitle(intent.getExtras().getString("title"));

                                        db.favoriteDao().deleteFavorite(favoriteEntitas);

                                        Log.d("DELETE", "DELETE DATA");
                                        Log.d("DELETE", "===========");

                                        Log.e("DELETE", "NAMA : " + movieDetailModelResponse.getId());
                                        Log.e("DELETE", "EMAIL : " + movieDetailModelResponse.getTitle());

                                        movieDetailFavorite.setImageResource(R.drawable.ic_favorite_border);
                                        Toast.makeText(MovieDetailActivity.this, "" + movieDetailModelResponse.getTitle() + " Was Removed from Favorite", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieDetailModel> call, @NonNull Throwable t) {

                    }
                });


                // service untuk movie image
                Call<MovieImages> movieImagesCall = retrofitService.getMovieImagesById(id, BuildConfig.THE_MOVIE_DB_API_KEY);

                movieImagesCall.enqueue(new Callback<MovieImages>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieImages> call, @NonNull Response<MovieImages> response) {
                        MovieImages movieImages = response.body();

                        progress.dismiss();

                        if (movieImages != null) {
                            ArrayList<MovieImagesBackdropsAndPoster> movieImagesBackdropsAndPosterArrayList = new ArrayList<>();

                            List<MovieImagesBackdropsAndPoster> movieImagesBackdropsList = movieImages.getBackdrops();
                            List<MovieImagesBackdropsAndPoster> movieImagesPostersList = movieImages.getPosters();

                            if (movieImagesBackdropsList != null && movieImagesBackdropsList.size() > 0) {
                                if (movieImagesPostersList != null && movieImagesPostersList.size() > 0) {
                                    movieImagesBackdropsAndPosterArrayList.addAll(movieImagesBackdropsList);
                                    movieImagesBackdropsAndPosterArrayList.addAll(movieImagesPostersList);
                                } else {
                                    movieImagesBackdropsAndPosterArrayList.addAll(movieImagesBackdropsList);
                                }
                            } else if (movieImagesPostersList != null && movieImagesPostersList.size() > 0) {
                                movieImagesBackdropsAndPosterArrayList.addAll(movieImagesPostersList);
                            } else {
                                movieImagesBackdropsAndPosterArrayList.clear();
                            }

                            if (movieImagesBackdropsAndPosterArrayList.size() > 0) {
                                // panggil adapter
                                MoviePosterImageAdapter moviePosterImageAdapter = new MoviePosterImageAdapter(MovieDetailActivity.this, movieImagesBackdropsAndPosterArrayList);
                                movieDetailImagesRecyclerView.setAdapter(moviePosterImageAdapter);

                                // animasi
                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);
                                movieDetailImagesRecyclerView.setLayoutAnimation(controller);
                                movieDetailImagesRecyclerView.scheduleLayoutAnimation();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieImages> call, @NonNull Throwable t) {

                    }
                });

                // service untuk movie video
                Call<MovieVideos> movieVideosCall = retrofitService.getMovieVideossById(id, BuildConfig.THE_MOVIE_DB_API_KEY);

                movieVideosCall.enqueue(new Callback<MovieVideos>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieVideos> call, @NonNull Response<MovieVideos> response) {
                        MovieVideos movieVideos = response.body();

                        if (movieVideos != null) {

                            List<MovieVideosResults> movieVideosResultsList = movieVideos.getResults();

                            if (movieVideosResultsList != null && movieVideosResultsList.size() > 0) {

                                movieDetailVideoLayout.setVisibility(View.VISIBLE);

                                // panggil adapter
                                MovieVideoAdapter adapter = new MovieVideoAdapter(MovieDetailActivity.this, movieVideosResultsList);
                                movieDetailVideoRecyclerView.setAdapter(adapter);

                                // animasi
                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this, R.anim.layout_slide_right);
                                movieDetailVideoRecyclerView.setLayoutAnimation(controller);
                                movieDetailVideoRecyclerView.scheduleLayoutAnimation();

                            } else {
                                movieDetailVideoLayout.setVisibility(View.GONE);
                            }
                        } else {
                            movieDetailVideoLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieVideos> call, @NonNull Throwable t) {

                    }
                });

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void prepareMovieDetail(MovieDetailModel movieDetailModelResponse) {

        String posterPath = movieDetailModelResponse.getPoster_path();
        String backdropPath = movieDetailModelResponse.getBackdrop_path();
        String title = movieDetailModelResponse.getTitle();
        String originalTitle = movieDetailModelResponse.getOriginal_title();
        String release = movieDetailModelResponse.getRelease_date();
        String language = movieDetailModelResponse.getOriginal_language();


        int runtime = movieDetailModelResponse.getRuntime();


        String voteAverage = Double.toString(movieDetailModelResponse.getVote_average());

        List<MovieDetailGenres> movieDetailGenresList = movieDetailModelResponse.getGenres();

        String releaseDate = movieDetailModelResponse.getRelease_date();
        String overview = movieDetailModelResponse.getOverview();


        // poster
        if (posterPath != null) {
            Picasso.with(this).load(posterPath).into(movieDetailPoster);
            movieDetailPoster.setVisibility(View.VISIBLE);
        } else {
            movieDetailPoster.setVisibility(View.GONE);
        }

        // backdrop
        Picasso.with(this).load(backdropPath).into(movieDetailBackdrop);

        // title
        if (title != null && title.length() > 0) {
            movieDetailTitle.setText(title);
        }

        // original title
        if (originalTitle != null) {
            if (originalTitle.length() > 0) {
                movieDetailOriginalTitle.setText(originalTitle);
                movieDetailOriginalTitleLayout.setVisibility(View.VISIBLE);
            } else {
                movieDetailOriginalTitleLayout.setVisibility(View.GONE);
            }
        } else {
            movieDetailOriginalTitleLayout.setVisibility(View.GONE);
        }

        // release
        if (release != null) {
            if (release.length() > 0) {
                movieDetailRelease.setText(releaseDate);
                movieDetailReleaseLayout.setVisibility(View.VISIBLE);
            } else {
                movieDetailReleaseLayout.setVisibility(View.GONE);
            }
        } else {
            movieDetailReleaseLayout.setVisibility(View.GONE);
        }

        // language
        if (language != null) {
            if (language.length() > 0) {
                movieDetailLanguage.setText(language);
                movieDetailLanguageLayout.setVisibility(View.VISIBLE);
            } else {
                movieDetailLanguageLayout.setVisibility(View.GONE);
            }
        } else {
            movieDetailLanguageLayout.setVisibility(View.GONE);
        }

        // runtime
        if (runtime != 0) {
            movieDetailRunningTime.setText(String.valueOf(runtime) + " minutes");
            movieDetailRunningTimeLayout.setVisibility(View.VISIBLE);
        } else {
            movieDetailRunningTimeLayout.setVisibility(View.GONE);
        }

        // vote average
        if (voteAverage != null) {
            movieDetailVoteAverage.setText(voteAverage + "/10");
        }

        // genre
        if (movieDetailGenresList != null && movieDetailGenresList.size() > 0) {

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < movieDetailGenresList.size(); i++) {

                if (i == movieDetailGenresList.size() - 1) {
                    builder.append(movieDetailGenresList.get(i).getName());
                } else {
                    builder.append(movieDetailGenresList.get(i).getName()).append(", ");
                }

                movieDetailGenres.setText(builder.toString());
            }
        }

        if (releaseDate != null && releaseDate.length() > 0) {
            String year = releaseDate.substring(0, 4);

            movieDetailReleaseDate.setText(year);
        }

        if (overview != null && overview.length() > 0) {
            movieDetailOverview.setText(overview);
        }

    }

    // set animation untuk back press
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
