package com.apps.nosacikal.removie;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.nosacikal.removie.Adapter.MovieListAdapter;
import com.apps.nosacikal.removie.Adapter.MovieRecomendedAdapter;
import com.apps.nosacikal.removie.Adapter.MovieTopRatedAdapter;
import com.apps.nosacikal.removie.Client.RetrofitClient;
import com.apps.nosacikal.removie.Interfaces.RetrofitService;
import com.apps.nosacikal.removie.Models.MovieModel;
import com.apps.nosacikal.removie.Models.MovieModelResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Kamis 13 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);

        final RecyclerView popularMovieRecyclerView = view.findViewById(R.id.popular_recycler_view);
        popularMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        final RecyclerView topRatedMovieRecyclerView = view.findViewById(R.id.toprated_recycler_view);
        topRatedMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        final RecyclerView recomendedRecyclerView = view.findViewById(R.id.recomended_recycler_view);
        recomendedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        final RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);

        Call<MovieModel> movieModelPopularCall = retrofitService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);

        movieModelPopularCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                MovieModel movieModel = response.body();

                if (movieModel != null) {
                    List<MovieModelResult> movieModelResultList = movieModel.getResults();
                    MovieListAdapter movieListAdapter = new MovieListAdapter(getActivity(), movieModelResultList);
                    popularMovieRecyclerView.setAdapter(movieListAdapter);

                    // animasi
//                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieListFragment.this.getContext(), R.anim.layout_slide_right);

//                    popularMovieRecyclerView.setLayoutAnimation(controller);
//                    popularMovieRecyclerView.scheduleLayoutAnimation();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Toast.makeText(MovieListFragment.this.getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        Call<MovieModel> movieModelTopRatedCall = retrofitService.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_KEY);

        movieModelTopRatedCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                MovieModel movieModel = response.body();

                if (movieModel != null) {
                    List<MovieModelResult> movieModelResultList = movieModel.getResults();
                    MovieTopRatedAdapter movieTopRatedAdapter = new MovieTopRatedAdapter(getActivity(), movieModelResultList);
                    topRatedMovieRecyclerView.setAdapter(movieTopRatedAdapter);

                    // animasi
//                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieListFragment.this.getContext(), R.anim.layout_slide_right);
//
//                    topRatedMovieRecyclerView.setLayoutAnimation(controller);
//                    topRatedMovieRecyclerView.scheduleLayoutAnimation();
                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Toast.makeText(MovieListFragment.this.getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });


        Call<MovieModel> movieModelRecomended = retrofitService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);

        movieModelRecomended.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                MovieModel movieModel = response.body();

                if (movieModel != null) {
                    List<MovieModelResult> movieModelResultList = movieModel.getResults();
                    MovieRecomendedAdapter movieRecomendedAdapter = new MovieRecomendedAdapter(getActivity(), movieModelResultList);
                    recomendedRecyclerView.setAdapter(movieRecomendedAdapter);

                    // animasi
//                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieListFragment.this.getContext(), R.anim.layout_slide_right);

//                    popularMovieRecyclerView.setLayoutAnimation(controller);
//                    popularMovieRecyclerView.scheduleLayoutAnimation();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Toast.makeText(MovieListFragment.this.getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
