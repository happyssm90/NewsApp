
package com.sabya.newsapp.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sabyasachi
 */
public class Clouds {

    @SerializedName("all")
    private Long mAll;

    public Long getAll() {
        return mAll;
    }

    public void setAll(Long all) {
        mAll = all;
    }

}
