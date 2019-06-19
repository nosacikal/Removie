package com.apps.nosacikal.removie.Models;

import java.util.List;

/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieDetailModel {

    private boolean adult;
    private String backdrop_path;
    private MovieDetailBelongsToCollection belongsToCollection;
    private int budget;
    private List<MovieDetailGenres> genres;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private List<MovieDetailProductionCompanies> production_companies;
    private List<MovieDetailProductionCountries> production_countries;
    private String release_date;
    private int revenue;
    private int runtime;
    private List<MovieDetailSpokenLanguages> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Double vote_average;
    private int vote_count;

    public MovieDetailModel() {
    }

    public MovieDetailModel(boolean adult, String backdrop_path, MovieDetailBelongsToCollection belongsToCollection, int budget, List<MovieDetailGenres> genres, String homepage, int id, String imdb_id, String original_language, String original_title, String overview, Double popularity, String poster_path, List<MovieDetailProductionCompanies> production_companies, List<MovieDetailProductionCountries> production_countries, String release_date, int revenue, int runtime, List<MovieDetailSpokenLanguages> spoken_languages, String status, String tagline, String title, boolean video, Double vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        // buat base url untuk poster
        String baseUrl = "http://image.tmdb.org/t/p/w500";

        return baseUrl + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public MovieDetailBelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(MovieDetailBelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<MovieDetailGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieDetailGenres> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        // buat base url untuk poster
        String baseUrl = "http://image.tmdb.org/t/p/w500";

        return baseUrl + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<MovieDetailProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<MovieDetailProductionCompanies> production_companies) {
        this.production_companies = production_companies;
    }

    public List<MovieDetailProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<MovieDetailProductionCountries> production_countries) {
        this.production_countries = production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<MovieDetailSpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<MovieDetailSpokenLanguages> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
