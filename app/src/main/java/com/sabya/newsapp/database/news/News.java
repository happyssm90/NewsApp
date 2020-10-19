package com.sabya.newsapp.database.news;

/**
 * Created by Sabyasachi
 */
public class News {
    public static final String TABLE_NAME = "news";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    private int mId;
    private String mNews;
    private String mTimestamp;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public News() {
    }

    public News(int id, String note, String timestamp) {
        this.mId = id;
        this.mNews = note;
        this.mTimestamp = timestamp;
    }

    public int getId() {
        return mId;
    }

    public String getNews() {
        return mNews;
    }

    public void setNews(String news) {
        this.mNews = news;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }
}
