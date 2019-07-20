package com.apps.nosacikal.removie;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.apps.nosacikal.removie.Adapter.MovieSearchAdapter;
import com.apps.nosacikal.removie.Client.RetrofitClient;
import com.apps.nosacikal.removie.Interfaces.RetrofitService;
import com.apps.nosacikal.removie.Models.MovieModel;
import com.apps.nosacikal.removie.Models.MovieModelResult;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Sabtu 22 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class SearchMovieFragment extends Fragment {


    private ProgressDialog progress;

    private EditText queryEditText;

    private ImageButton querySearchButton;

    private RecyclerView resultRecyclerView;

    private RetrofitService retrofitService;

    private MovieSearchAdapter movieSearchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_movie_fragment, container, false);

        // progress dialog
        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        // disable keyboard waktu run app
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        queryEditText = view.findViewById(R.id.query_edit_text);
        querySearchButton = view.findViewById(R.id.query_search_button);
        resultRecyclerView = view.findViewById(R.id.result_recycler_view);

//        resultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        resultRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Paper.init(Objects.requireNonNull(getContext()));

        retrofitService = RetrofitClient.getClient().create(RetrofitService.class);


        // ambil result dari paper db

        if (Paper.book().read("cache") != null) {

            String result = Paper.book().read("cache");

            if (Paper.book().read("source") != null) {
                String source = Paper.book().read("source");

                if (source.equals("movie")) {
                    MovieModel movieResponse = new Gson().fromJson(result, MovieModel.class);

                    progress.dismiss();

                    if (movieResponse != null) {
                        List<MovieModelResult> movieResponseResults = movieResponse.getResults();

                        movieSearchAdapter = new MovieSearchAdapter(getActivity(), movieResponseResults);

                        resultRecyclerView.setAdapter(movieSearchAdapter);


                        // store result ke offline database (paper database)
                        Paper.book().write("cache", new Gson().toJson(movieResponse));

                        // store category untuk set spinner
                        Paper.book().write("source", "movie");

                    }
                }
            }
        }


        // get query dari user
        querySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (queryEditText.getText() != null) {
                    String query = queryEditText.getText().toString();

                    if (query.equals("") || query.equals(" ")) {
                        Toast.makeText(SearchMovieFragment.this.getContext(), "Please enter any text", Toast.LENGTH_SHORT).show();
                    } else {
                        queryEditText.setText("");

                        // ambil query dan buang spasi
                        String finalQuery = query.replaceAll(" ", "+");

                        // fetch data dari retrofit
                        Call<MovieModel> movieResponseCall = retrofitService.getMoviesByQuery(BuildConfig.THE_MOVIE_DB_API_KEY, finalQuery);

                        movieResponseCall.enqueue(new Callback<MovieModel>() {
                            @Override
                            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                                MovieModel movieResponse = response.body();

                                progress.dismiss();

                                if (movieResponse != null) {
                                    List<MovieModelResult> movieResponseResults = movieResponse.getResults();

                                    movieSearchAdapter = new MovieSearchAdapter(getActivity(), movieResponseResults);

                                    resultRecyclerView.setAdapter(movieSearchAdapter);

                                    // store result ke offline database (paper database)
                                    Paper.book().write("cache", new Gson().toJson(movieResponse));

                                    // store search movie
                                    Paper.book().write("source", "movie");

                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                                progress.dismiss();
                                Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }


            }
        });


        return view;
    }
}
