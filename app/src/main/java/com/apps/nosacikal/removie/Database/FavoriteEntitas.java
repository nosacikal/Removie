package com.apps.nosacikal.removie.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*
 * Rabu 3 Juli 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 *
 * */

@Entity
public class FavoriteEntitas {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
