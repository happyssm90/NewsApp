package com.sabya.newsapp.navigation;

/**
 * Created by Ravi on 29/07/15.
 */

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sabya.newsapp.R;
import com.sabya.newsapp.model.NavDrawerItem;
import com.sabya.newsapp.model.weather.Forecast;
import com.sabya.newsapp.utils.ApiInterface;
import com.sabya.newsapp.utils.AppUtils;
import com.sabya.newsapp.utils.ConstUtils;
import com.sabya.newsapp.utils.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sabyasachi
 */
public class NavigationDrawerFragment extends Fragment {

    private static String TAG = NavigationDrawerFragment.class.getSimpleName();

    private static final String APP_ID = "cc5db9cf47e272b14285b52bf0e71505";

    private static final String CITY_NAME = "Bangalore";

    private FragmentDrawerListener mDrawerListener;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;
    private static String[] titles = null;
    private TextView mCityName;
    private TextView mWeatherData;
    private TextView mTemperature;
    private TextView mDate;
    private TextView mMinTemp;
    private TextView mMaxTemp;
    private ProgressBar mProgressBar;

    public NavigationDrawerFragment() {
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.mDrawerListener = listener;
    }

    public static List<NavDrawerItem> getNavigationDrawerData() {
        List<NavDrawerItem> data = new ArrayList<>();
        for (String title : titles) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(title);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_navigation_drawer, container, false);
        initViews(view);
        if (getActivity() != null && AppUtils.isNetworkConnected(getActivity())) {
            getWeatherData();
        } else {
            Toast.makeText(getActivity(), "Check for internet connection", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.weather_progress_bar);
        mCityName = view.findViewById(R.id.text_view_city);
        mDate = view.findViewById(R.id.text_view_date);
        mWeatherData = view.findViewById(R.id.text_view_city_weather_details);
        mTemperature = view.findViewById(R.id.text_view_temperature);
        mMinTemp = view.findViewById(R.id.text_view_min_temperature);
        mMaxTemp = view.findViewById(R.id.text_view_max_temperature);
        RecyclerView mRecyclerView = view.findViewById(R.id.drawerList);
        NavigationDrawerAdapter mNavigationDrawerAdapter = new NavigationDrawerAdapter(getActivity(), getNavigationDrawerData(), new NavigationDrawerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                mDrawerListener.onDrawerItemSelected(view, pos);
                mDrawerLayout.closeDrawer(mContainerView);
            }
        });
        mRecyclerView.setAdapter(mNavigationDrawerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getWeatherData() {
        ApiInterface apiService =
                RestClient.getClient(ConstUtils.CODE_WEATHER).create(ApiInterface.class);
        Call<Forecast> call = apiService.getWeather(CITY_NAME, APP_ID);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(@NonNull Call<Forecast> call, @NonNull Response<Forecast> response) {
                Forecast forecastData = response.body();
                if (forecastData != null) {
                    updateWeatherUI(forecastData);
                }
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<Forecast> call, @NonNull Throwable throwable) {
                Log.e(TAG, "Error while getting weather data.");
                Toast.makeText(getActivity(), "Failed to get weather data", Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateWeatherUI(Forecast forecastData) {
        mCityName.setText(forecastData.getName());
        mDate.setText(AppUtils.getCurrentDate());
        mWeatherData.setText(forecastData.getWeather().get(0).getDescription());
        mTemperature.setText(new StringBuilder().append(AppUtils.getTempInCelsius(forecastData.getMain().getTemp())).append(" \u2103"));
        mMinTemp.setText(new StringBuilder().append("Min  : ").append(AppUtils.getTempInCelsius(forecastData.getMain().getTempMin())));
        mMaxTemp.setText(new StringBuilder().append("Max : ").append(AppUtils.getTempInCelsius(forecastData.getMain().getTempMax())));
    }

    public void setUpDrawer(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        if (getActivity() == null) {
            return;
        }
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getActivity() != null) {
                    getActivity().invalidateOptionsMenu();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getActivity() != null) {
                    getActivity().invalidateOptionsMenu();
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }
}
