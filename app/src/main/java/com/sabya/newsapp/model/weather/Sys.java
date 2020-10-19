
package com.sabya.newsapp.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sabyasachi
 */
public class Sys {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("sunrise")
    private Long mSunrise;
    @SerializedName("sunset")
    private Long mSunset;

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Long getSunrise() {
        return mSunrise;
    }

    public void setSunrise(Long sunrise) {
        mSunrise = sunrise;
    }

    public Long getSunset() {
        return mSunset;
    }

    public void setSunset(Long sunset) {
        mSunset = sunset;
    }

}
