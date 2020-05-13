package com.sict.mobile.tada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderItem {

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("description")
    @Expose
    private String description;

    private int idImage;

    public SliderItem(int idImage) {
        this.idImage = idImage;
    }

    public SliderItem(String path) {
        this.path = path;
    }

    public SliderItem(String path, String description) {
        this.path = path;
        this.description = description;
    }

    public SliderItem(int idImage, String description) {
        this.description = description;
        this.idImage = idImage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
