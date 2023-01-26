package com.coder.elaundry_apps.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();
}
