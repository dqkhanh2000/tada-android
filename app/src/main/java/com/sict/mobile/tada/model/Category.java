package com.sict.mobile.tada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("CategoryName")
    @Expose
    private String name;

    @SerializedName("CategoryCode")
    @Expose
    private String code;

    @SerializedName("Images")
    @Expose
    String image;

    public Category(String name, String code, String image) {
        this.name = name;
        this.code = code;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageURL() {
        return image;
    }

    public void setImageURL(String image) {
        this.image = image;
    }
}
