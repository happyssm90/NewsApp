package com.sabya.newsapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.sabya.newsapp.R;
import com.sabya.newsapp.database.DatabaseHelper;
import com.sabya.newsapp.model.news.Article;

/**
 * Created by Sabyasachi
 */
public class NewsDetailsActivity extends AppCompatActivity {
    private static String TAG = NewsDetailsActivity.class.getSimpleName();

    private Article mArticle;
    private ImageView mNewsImageView;
    private TextView mNewsDesc;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_news_details);
        if (getIntent() != null) {
            mArticle = (Article) getIntent().getSerializableExtra("news_data");
        }
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.str_back);
        }
        initViews();
        populateNewsData();
    }

    private void populateNewsData() {
        Glide.with(this)
                .load(mArticle.getUrlToImage())
                .into(mNewsImageView);
        mNewsDesc.setText(mArticle.getDescription());
    }

    private void initViews() {
        mDatabaseHelper = new DatabaseHelper(this);
        mNewsImageView = findViewById(R.id.news_image_view);
        mNewsDesc = findViewById(R.id.news_desc);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        } else if (itemId == R.id.action_bookmark) {
            insertNewsToDB(mArticle.getDescription());
        } else if (itemId == R.id.action_save) {
            Toast.makeText(this, "Save clicked", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.action_send) {
            Toast.makeText(this, "Send clicked", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.action_share) {
            Toast.makeText(this, "Share clicked", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private void insertNewsToDB(String news) {
        mDatabaseHelper.insertNews(news);
        Toast.makeText(this, "Bookmarked successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }
}
