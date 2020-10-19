
package com.sabya.newsapp.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sabyasachi
 */
public class Rain {
    @SerializedName("1h")
    private Double mH;

    public Double getH() {
        return mH;
    }

    public void setH(Double h) {
        mH = h;
    }

}
