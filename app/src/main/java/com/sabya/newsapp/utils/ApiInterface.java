package com.sabya.newsapp.utils;

import com.sabya.newsapp.model.news.NewsHeadLine;
import com.sabya.newsapp.model.weather.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sabyasachi
 */
public interface ApiInterface {

    @GET("data/2.5/weather")
    Call<Forecast> getWeather(@Query("q") String cityID, @Query("appid") String appID);

    @GET("top-headlines")
    Call<NewsHeadLine> getNewsHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);
}
