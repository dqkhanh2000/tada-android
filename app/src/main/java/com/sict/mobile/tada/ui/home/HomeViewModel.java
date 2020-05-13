package com.sict.mobile.tada.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sict.mobile.tada.R;
import com.sict.mobile.tada.connection.RetrofitClient;
import com.sict.mobile.tada.model.ProductItem;
import com.sict.mobile.tada.model.SliderItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<SliderItem>> dataSlider = new MutableLiveData<>();
    public MutableLiveData<List<ProductItem>> dataTopForWomen = new MutableLiveData<>();
    public MutableLiveData<List<ProductItem>> dataTopForMen = new MutableLiveData<>();

    public void setDataTopForWomen() {
        List<ProductItem> data = new ArrayList<>();
        RetrofitClient.getAPI().getTopForWomen().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    for(ProductItem item : response.body()){
                        item.setUrl(RetrofitClient.BASE_URL + "image/" +item.getUrl());
                        data.add(item);
                    }
                    dataTopForWomen.postValue(data);
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }
    
    public void setDataTopForMen() {

        List<ProductItem> data = new ArrayList<>();
        RetrofitClient.getAPI().getTopForMen().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    for(ProductItem item : response.body()){
                        item.setUrl(RetrofitClient.BASE_URL + "image/" +item.getUrl());
                        data.add(item);
                    }
                    dataTopForMen.postValue(data);
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });

    }

    public void getDataSlider(){

        List<SliderItem> data = new ArrayList<>();
        RetrofitClient.getAPI().getSliderItem().enqueue(new Callback<List<SliderItem>>() {
            @Override
            public void onResponse(Call<List<SliderItem>> call, Response<List<SliderItem>> response) {
                if(response.isSuccessful()) {
                    for (SliderItem item : response.body()) {
                        item.setPath(RetrofitClient.BASE_URL + item.getPath());
                        data.add(item);
                    }
                    dataSlider.postValue(data);
                }
            }

            @Override
            public void onFailure(Call<List<SliderItem>> call, Throwable t) {

            }
        });
    }
}
