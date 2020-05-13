package com.sict.mobile.tada.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "http://reycs.cf/";
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient(){
        if(sRetrofit == null)
            sRetrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL+"api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        return sRetrofit;
    }

    public static API getAPI(){
        return getClient().create(API.class);
    }

}
