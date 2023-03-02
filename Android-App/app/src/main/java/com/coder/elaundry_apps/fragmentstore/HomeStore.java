package com.coder.elaundry_apps.fragmentstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.adapter.OrderAdapter;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.APIError;
import com.coder.elaundry_apps.model.OrderModel;
import com.coder.elaundry_apps.response.GetOrder;
import com.coder.elaundry_apps.utils.ErrorUtils;
import com.coder.elaundry_apps.utils.HelperUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class HomeStore extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView gridOrder;
    private OrderAdapter orderAdapter;
    private List<OrderModel> daftarOrder;

    public HomeStore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_home_store, container, false);
        this.dataInit(mview);
        this.setupRecyclerView();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                Constants.KEY_USER, Context.MODE_PRIVATE);
        String idUser = sharedPreferences.getString("idUser", "");
        this.setData(idUser,getContext());
        return mview;
    }

    private void dataInit(View mview) {
        gridOrder = mview.findViewById(R.id.rcOrder);
        progressBar = mview.findViewById(R.id.progressBar);
        this.hideDialog();
    }

    private void showDialog() {
        if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideDialog() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        daftarOrder = new ArrayList<>();
        orderAdapter = new OrderAdapter(daftarOrder,getContext());
        gridOrder.setLayoutManager(linearLayoutManager);
        gridOrder.setAdapter(orderAdapter);
    }

    private void setData(String idUser, Context mContext)
    {
        this.showDialog();
        try{
            Call<GetOrder> call= APIService.Factory.create(mContext).getOrderStore(idUser);
            call.enqueue(new Callback<GetOrder>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {
                    hideDialog();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            daftarOrder = response.body().getDaftarOrder();
                            orderAdapter.replaceData(daftarOrder);
                        }
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        if (error != null) {
                            HelperUtils.pesan(mContext, idUser);
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<GetOrder> call, Throwable t) {
                    hideDialog();
                    if (t instanceof NoConnectivityException) {
                        HelperUtils.pesan(mContext, t.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            hideDialog();
            e.printStackTrace();
            HelperUtils.pesan(mContext, e.getMessage());
        }
    }
}