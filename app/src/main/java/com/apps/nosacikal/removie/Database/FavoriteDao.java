package com.apps.nosacikal.removie.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/*
 * Rabu 3 Juli 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 *
 * */

@Dao
public interface FavoriteDao {

    @Insert
    void tambahFavorite(FavoriteEntitas favoriteEntitas);

    @Delete
    public void deleteFavorite(FavoriteEntitas favoriteEntitas);

    @Query("SELECT * FROM FavoriteEntitas")
    List<FavoriteEntitas> tampilFavorite();

    @Query("SELECT * FROM FavoriteEntitas WHERE id LIKE:id")
    boolean tampilFavoriteById(String id);
}
