package com.apps.nosacikal.removie.Models;

/*
 * Jumat 14 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class MovieDetailProductionCompanies {

    private int id;
    private String logo_path;
    private String name;
    private String origin_country;

    public MovieDetailProductionCompanies() {
    }

    public MovieDetailProductionCompanies(int id, String logo_path, String name, String origin_country) {
        this.id = id;
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo_path() {
        // buat base url untuk poster
        String baseUrl = "http://image.tmdb.org/t/p/w500";

        return baseUrl + logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }
}
