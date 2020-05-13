package com.sict.mobile.tada.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductItem implements Parcelable {
    @SerializedName("name")
    @Expose
    private String productName;

    @SerializedName("code")
    @Expose
    private String productCode;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String url;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("sale-off")
    @Expose
    private int saleOff;

    @SerializedName("hot")
    @Expose
    private boolean hot;

    @SerializedName("colors")
    @Expose
    private ProductColor[] productColors;

    public ProductItem(String productName, String url, int price, int saleOff) {
        this.productName = productName;
        this.url = url;
        this.price = price;
        this.saleOff = saleOff;
    }

    public ProductItem(String productName, int id, int price, int saleOff) {
        this.productName = productName;
        this.id = id;
        this.price = price;
        this.saleOff = saleOff;
    }

    public ProductItem(String productName, String url) {
        this.productName = productName;
        this.url = url;
    }

    public ProductItem(String productName, int id) {
        this.productName = productName;
        this.id = id;
    }

    protected ProductItem(Parcel in) {
        productName = in.readString();
        productCode = in.readString();
        description = in.readString();
        url = in.readString();
        id = in.readInt();
        price = in.readInt();
        saleOff = in.readInt();
        hot = in.readByte() != 0;
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    public int calculateSaleOff() {
        return (int) this.price - this.price * this.saleOff / 100;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(int saleOff) {
        this.saleOff = saleOff;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public ProductColor[] getProductColors() {
        return productColors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductColors(ProductColor[] productColors) {
        this.productColors = productColors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productCode);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeInt(id);
        dest.writeInt(price);
        dest.writeInt(saleOff);
        dest.writeByte((byte) (hot ? 1 : 0));
        dest.writeTypedArray(productColors, flags);
    }
}
