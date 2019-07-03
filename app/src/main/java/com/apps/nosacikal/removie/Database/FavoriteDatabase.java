package com.apps.nosacikal.removie.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/*
 * Rabu 3 Juli 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 *
 * */

@Database(entities = {FavoriteEntitas.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();
}
