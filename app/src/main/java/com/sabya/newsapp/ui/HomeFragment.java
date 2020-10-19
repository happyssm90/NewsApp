package com.sabya.newsapp.ui;

/**
 * Created by Ravi on 29/07/15.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sabya.newsapp.R;
import com.sabya.newsapp.adapter.NewsCategoryPagerAdapter;

/**
 * Created by Sabyasachi
 */
public class HomeFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment_home, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void setupViewPager(ViewPager viewPager) {
        NewsCategoryPagerAdapter adapter = new NewsCategoryPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(), "MOVIES");
        adapter.addFragment(new NewsFragment(), "FASHION");
        adapter.addFragment(new NewsFragment(), "MODEL");
        adapter.addFragment(new NewsFragment(), "LOCAL");
        adapter.addFragment(new NewsFragment(), "ALL");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
