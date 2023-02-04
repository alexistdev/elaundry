package com.coder.elaundry_apps.fragmentUser;

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
import com.coder.elaundry_apps.adapter.HistoryAdapter;
import com.coder.elaundry_apps.adapter.LaundryAdapter;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.APIError;
import com.coder.elaundry_apps.model.HistoryModel;
import com.coder.elaundry_apps.response.GetHistory;
import com.coder.elaundry_apps.utils.ErrorUtils;
import com.coder.elaundry_apps.utils.HelperUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LaundryUser extends Fragment {
    private RecyclerView gridHistory;
    private HistoryAdapter historyAdapter;
    private List<HistoryModel> daftarHistory;

    public LaundryUser() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_laundry_user, container, false);
        dataInit(mview);
        setupRecyclerView();
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(
                Constants.KEY_USER, Context.MODE_PRIVATE);
        String idUser = sharedPreferences.getString("idUser", "");
        setData(getContext(),idUser);
        return mview;
    }

    private void dataInit(View mview) {
        gridHistory = mview.findViewById(R.id.rcHistory);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        gridHistory.setLayoutManager(linearLayoutManager);
        gridHistory.setAdapter(historyAdapter);
    }



    public void setData(Context mContext,String idUser) {
        try{
            Call<GetHistory> call= APIService.Factory.create(mContext).getHistory(idUser);
            call.enqueue(new Callback<GetHistory>() {
                @Override
                public void onResponse(Call<GetHistory> call, Response<GetHistory> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            daftarHistory =response.body().getDaftarHistory();
                            historyAdapter.replaceData(daftarHistory);
                        }
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        if (error != null) {
                            HelperUtils.pesan(mContext, error.message());
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetHistory> call, Throwable t) {

                    if (t instanceof NoConnectivityException) {
                        HelperUtils.pesan(mContext, t.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            HelperUtils.pesan(getContext(), e.getMessage());
        }
    }
}