package com.sabya.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sabya.newsapp.R;
import com.sabya.newsapp.model.news.Article;

import java.util.List;

/**
 * Created by Sabyasachi M
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> mData;
    private LayoutInflater inflater;
    private Context mContext;
    private final NewsAdapter.OnNewsItemClickListener mListener;

    public NewsAdapter(Context context, List<Article> data, NewsAdapter.OnNewsItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.mData = data;
        this.mListener = listener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_news_item, parent, false);
        return new NewsAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsViewHolder holder, final int position) {
        Article current = mData.get(position);
        holder.newsTitle.setText(current.getTitle());
        holder.newsRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mData.get(position));
            }
        });
        Glide.with(mContext)
                .load(mData.get(position).getUrlToImage())
                .into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsImage;
        RelativeLayout newsRootLayout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsRootLayout = itemView.findViewById(R.id.newsRootLayout);
        }
    }

    public interface OnNewsItemClickListener {
        void onItemClick(Article article);
    }
}
