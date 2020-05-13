package com.sict.mobile.tada.connection;

import com.sict.mobile.tada.model.Category;
import com.sict.mobile.tada.model.ProductItem;
import com.sict.mobile.tada.model.SliderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("get-slider")
    Call<List<SliderItem>> getSliderItem();

    @GET("get-top-for-women")
    Call<List<ProductItem>> getTopForWomen();

    @GET("get-top-for-men")
    Call<List<ProductItem>> getTopForMen();

    @GET("get-category")
    Call<List<Category>> getCategory();

    @GET("collection/{category_code}")
    Call<List<ProductItem>> collection(@Path("category_code") String categoryCode);
}
