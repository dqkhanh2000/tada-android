package com.sict.mobile.tada.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductColor implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("images")
    @Expose
    private String[] images;

    @SerializedName("sizes")
    @Expose
    private ProductSize[] productSize;

    public ProductColor(int id, String color, String[] images, ProductSize[] productSize) {
        this.id = id;
        this.color = color;
        this.images = images;
        this.productSize = productSize;
    }

    protected ProductColor(Parcel in) {
        id = in.readInt();
        color = in.readString();
        images = in.createStringArray();
    }


    public static final Creator<ProductColor> CREATOR = new Creator<ProductColor>() {
        @Override
        public ProductColor createFromParcel(Parcel in) {
            return new ProductColor(in);
        }

        @Override
        public ProductColor[] newArray(int size) {
            return new ProductColor[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public ProductSize[] getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize[] productSize) {
        this.productSize = productSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(color);
        dest.writeStringArray(images);
        dest.writeTypedArray(productSize, flags);
    }
}
