package com.sabya.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sabya.newsapp.R;
import com.sabya.newsapp.adapter.NewsAdapter;
import com.sabya.newsapp.model.news.Article;
import com.sabya.newsapp.model.news.NewsHeadLine;
import com.sabya.newsapp.utils.ApiInterface;
import com.sabya.newsapp.utils.AppUtils;
import com.sabya.newsapp.utils.ConstUtils;
import com.sabya.newsapp.utils.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sabyasachi
 */
public class NewsFragment extends Fragment implements NewsAdapter.OnNewsItemClickListener {
    private static final String TAG = NewsFragment.class.getSimpleName();

    private ProgressBar mProgressBar;

    private RecyclerView mNewsRecyclerView;

    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_news, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.news_progress_bar);
        mNewsRecyclerView = view.findViewById(R.id.news_recycler_view);
        mNewsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null && AppUtils.isNetworkConnected(getActivity())) {
            getNews();
        } else {
            Log.i(TAG, "No internet connection.");
        }
    }

    private void getNews() {
        mNewsRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                RestClient.getClient(ConstUtils.CODE_NEWS).create(ApiInterface.class);
        Call<NewsHeadLine> call = apiService.getNewsHeadlines("US", "a15d74ee5e924dc2825049427836b66d");
        call.enqueue(new Callback<NewsHeadLine>() {
            @Override
            public void onResponse(@NonNull Call<NewsHeadLine> call, @NonNull Response<NewsHeadLine> response) {
                NewsHeadLine newsHeadLine = response.body();
                if (newsHeadLine != null) {
                    updateNewsHeadLines(newsHeadLine);
                }
                mNewsRecyclerView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<NewsHeadLine> call, @NonNull Throwable throwable) {
                Log.e(TAG, "Error while getting weather data.");
                Toast.makeText(getActivity(), "Failed to get news data", Toast.LENGTH_LONG).show();
                mNewsRecyclerView.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateNewsHeadLines(NewsHeadLine newsHeadLine) {
        mNewsRecyclerView.setAdapter(new NewsAdapter(getActivity(), newsHeadLine.getArticles(), this));
    }

    @Override
    public void onItemClick(Article article) {
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        intent.putExtra("news_data", article);
        startActivity(intent);
    }
}
