package com.sabya.newsapp.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabya.newsapp.R;
import com.sabya.newsapp.model.NavDrawerItem;

import java.util.List;

/**
 * Created by Sabyasachi
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.DrawerViewHolder> {
    private List<NavDrawerItem> mData;
    private LayoutInflater inflater;
    private final OnItemClickListener mListener;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data, OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.mData = data;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_nav_drawer_row_item, parent, false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, final int position) {
        NavDrawerItem current = mData.get(position);
        holder.title.setText(current.getTitle());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class DrawerViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RelativeLayout rootLayout;

        public DrawerViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rootLayout = itemView.findViewById(R.id.root_item_layout);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }
}
