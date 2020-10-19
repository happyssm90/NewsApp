package com.sabya.newsapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sabya.newsapp.database.news.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabyasachi
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "news_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(News.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + News.TABLE_NAME);
        onCreate(db);
    }

    public long insertNews(String note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(News.COLUMN_NOTE, note);
        long id = sqLiteDatabase.insert(News.TABLE_NAME, null, values);
        sqLiteDatabase.close();
        return id;
    }

    public News getNewsById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(News.TABLE_NAME,
                new String[]{News.COLUMN_ID, News.COLUMN_NOTE, News.COLUMN_TIMESTAMP},
                News.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        News news = new News(
                cursor.getInt(cursor.getColumnIndex(News.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(News.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(News.COLUMN_TIMESTAMP)));
        cursor.close();
        return news;
    }

    public List<News> getAllNews() {
        List<News> notes = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + News.TABLE_NAME + " ORDER BY " +
                News.COLUMN_TIMESTAMP + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                News note = new News();
                note.setId(cursor.getInt(cursor.getColumnIndex(News.COLUMN_ID)));
                note.setNews(cursor.getString(cursor.getColumnIndex(News.COLUMN_NOTE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(News.COLUMN_TIMESTAMP)));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return notes;
    }

    public int getNewsCount() {
        String countQuery = "SELECT  * FROM " + News.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateNews(News news) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(News.COLUMN_NOTE, news.getNews());
        return sqLiteDatabase.update(News.TABLE_NAME, values, News.COLUMN_ID + " = ?",
                new String[]{String.valueOf(news.getId())});
    }

    public void deleteNews(News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(News.TABLE_NAME, News.COLUMN_ID + " = ?",
                new String[]{String.valueOf(news.getId())});
        db.close();
    }
}
