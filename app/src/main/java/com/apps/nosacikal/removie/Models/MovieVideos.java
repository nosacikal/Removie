package com.apps.nosacikal.removie.Models;

import java.util.List;

/*
 * Rabu 19 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieVideos {

    private int id;
    private List<MovieVideosResults> results;

    public MovieVideos() {
    }

    public MovieVideos(int id, List<MovieVideosResults> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieVideosResults> getResults() {
        return results;
    }

    public void setResults(List<MovieVideosResults> results) {
        this.results = results;
    }
}
