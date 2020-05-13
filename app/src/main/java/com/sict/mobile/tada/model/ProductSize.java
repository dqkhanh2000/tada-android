package com.sict.mobile.tada.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSize implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("available")
    @Expose
    private boolean isAvailable;

    public ProductSize(int id, String size, boolean isAvailable) {
        this.id = id;
        this.size = size;
        this.isAvailable = isAvailable;
    }

    protected ProductSize(Parcel in) {
        id = in.readInt();
        size = in.readString();
        isAvailable = in.readByte() != 0;
    }

    public static final Creator<ProductSize> CREATOR = new Creator<ProductSize>() {
        @Override
        public ProductSize createFromParcel(Parcel in) {
            return new ProductSize(in);
        }

        @Override
        public ProductSize[] newArray(int size) {
            return new ProductSize[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(size);
        dest.writeByte((byte) (isAvailable ? 1 : 0));
    }
}
