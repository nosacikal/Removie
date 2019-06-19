package com.apps.nosacikal.removie.Models;

/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieDetailBelongsToCollection {

    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public MovieDetailBelongsToCollection() {
    }

    public MovieDetailBelongsToCollection(int id, String name, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        // buat base url untuk poster
        String baseUrl = "http://image.tmdb.org/t/p/w500";
        return baseUrl + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        // buat base url untuk poster
        String baseUrl = "http://image.tmdb.org/t/p/w500";

        return baseUrl + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
