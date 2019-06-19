package com.apps.nosacikal.removie.Interfaces;

import com.apps.nosacikal.removie.Models.MovieDetailModel;
import com.apps.nosacikal.removie.Models.MovieImages;
import com.apps.nosacikal.removie.Models.MovieModel;
import com.apps.nosacikal.removie.Models.MovieVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
 * Kamis 13 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public interface RetrofitService {

    @GET("movie/popular")
    Call<MovieModel> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieModel> getTopRatedMovies(@Query("api_key") String api_key);

    // service untuk detail movie
    @GET("movie/{movie_id}")
    Call<MovieDetailModel> getMovieDetailById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    // service untuk images movie
    @GET("movie/{movie_id}/images")
    Call<MovieImages> getMovieImagesById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    // service untuk video movie
    @GET("movie/{movie_id}/videos")
    Call<MovieVideos> getMovieVideossById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

}
