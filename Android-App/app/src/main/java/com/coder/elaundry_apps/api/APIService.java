package com.coder.elaundry_apps.api;

import android.content.Context;

import com.coder.elaundry_apps.BuildConfig;
import com.coder.elaundry_apps.model.LoginModel;
import com.coder.elaundry_apps.model.OrderModel;
import com.coder.elaundry_apps.response.GetHistory;
import com.coder.elaundry_apps.response.GetLaundry;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    String urlAPI = Constants.urlAPI;

    @GET(urlAPI+"list_laundry")
    Call<GetLaundry> getLaundry();

    @GET(urlAPI+"history")
    Call<GetHistory> getHistory(@Query("user_id") String userId);

    @FormUrlEncoded
    @POST(urlAPI+"auth")
    Call<LoginModel> loginUser(@Field("email") String email,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST(urlAPI+"order")
    Call<OrderModel> jemput(@Field("store_id") int idLaundry,
                            @Field("user_id") String idUser,
                            @Field("satuan") int satuan,
                            @Field("phone") String phone);

    class Factory {
        public static APIService create(Context mContext) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.addInterceptor(new NetworkConnectionInterceptor(mContext));
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            OkHttpClient client = builder.addInterceptor(logging).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}
