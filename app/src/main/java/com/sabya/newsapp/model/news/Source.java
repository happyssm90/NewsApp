
package com.sabya.newsapp.model.news;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sabyasachi
 */
public class Source implements Serializable {

    @SerializedName("id")
    private Object mId;
    @SerializedName("name")
    private String mName;

    public Object getId() {
        return mId;
    }

    public void setId(Object id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
