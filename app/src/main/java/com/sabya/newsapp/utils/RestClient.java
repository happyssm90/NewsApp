package com.sabya.newsapp.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sabyasachi
 */
public class RestClient {
    public static final String BASE_URL_WEATHER = "http://api.openweathermap.org/";

    public static final String BASE_URL_NEWS = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(int code) {
        switch (code) {
            case ConstUtils.CODE_WEATHER:
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL_WEATHER)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                break;
            case ConstUtils.CODE_NEWS:
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL_NEWS)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                break;
            default:
                break;
        }
        return retrofit;
    }
}
