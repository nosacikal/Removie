package com.apps.nosacikal.removie.Models;

import java.util.List;

/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieImages {

    private int id;
    private List<MovieImagesBackdropsAndPoster> backdrops;
    private List<MovieImagesBackdropsAndPoster> posters;

    public MovieImages() {
    }

    public MovieImages(int id, List<MovieImagesBackdropsAndPoster> backdrops, List<MovieImagesBackdropsAndPoster> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieImagesBackdropsAndPoster> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<MovieImagesBackdropsAndPoster> backdrops) {
        this.backdrops = backdrops;
    }

    public List<MovieImagesBackdropsAndPoster> getPosters() {
        return posters;
    }

    public void setPosters(List<MovieImagesBackdropsAndPoster> posters) {
        this.posters = posters;
    }
}
