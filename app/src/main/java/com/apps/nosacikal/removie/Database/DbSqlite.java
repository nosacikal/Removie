package com.apps.nosacikal.removie.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Minggu 16 Juni 2019
 * 10116062
 * Nosa Cikal Daputra
 * AKB-2
 * */

public class DbSqlite extends SQLiteOpenHelper {

    private static final String DB_NAME = "Removie";
    private static final int DB_VER = 1;

    public DbSqlite(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE favorite(id TEXT PRIMARY KEY, title TEXT);");
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(String.format("DROP TABLE IF EXISTS favorite"));

        // Create tables again
        onCreate(db);
    }


    public void addToFavorite(String movieId) {
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("INSERT INTO favorite(id) VALUES('"+movieId+"');");
        db.execSQL(query);
    }

    public void removeFromFavorite(String movieId) {
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("DELETE FROM favorite WHERE id='"+movieId+"';");
        db.execSQL(query);
    }

    public boolean isFavorite(String movieId) {
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("SELECT * FROM favorite WHERE id='"+movieId+"';");
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
