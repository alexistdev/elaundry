package com.coder.elaundry_apps.fragmentUser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.adapter.LaundryAdapter;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.APIError;
import com.coder.elaundry_apps.model.LaundryModel;
import com.coder.elaundry_apps.response.GetLaundry;
import com.coder.elaundry_apps.utils.ErrorUtils;
import com.coder.elaundry_apps.utils.HelperUtils;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class HomeUser extends Fragment {
    private RecyclerView gridLaundry;
    private LaundryAdapter laundryAdapter;
    private List<LaundryModel> daftarLaundry;
    private ProgressBar progressBar;

    public HomeUser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_home_user, container, false);
        dataInit(mview);
        setupRecyclerView();
        setData(getContext());
        return mview;
    }

    private void dataInit(View mview) {
        gridLaundry = mview.findViewById(R.id.rcLaundry);
        progressBar = mview.findViewById(R.id.progressBar);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        laundryAdapter = new LaundryAdapter(new ArrayList<>());
        gridLaundry.setLayoutManager(linearLayoutManager);
        gridLaundry.setAdapter(laundryAdapter);
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

    public void setData(Context mContext) {
        this.showDialog();
        try {
            Call<GetLaundry> call = APIService.Factory.create(mContext).getLaundry();
            call.enqueue(new Callback<GetLaundry>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<GetLaundry> call, Response<GetLaundry> response) {
                    hideDialog();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            daftarLaundry =response.body().getDaftarLaundry();
                            laundryAdapter.replaceData(daftarLaundry);
                        }
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        if (error != null) {
                            HelperUtils.pesan(mContext, error.message());
                        }
                    }
                }

                @EverythingIsNonNull
                @Override
                public void onFailure(Call<GetLaundry> call, Throwable t) {
                    hideDialog();
                    if (t instanceof NoConnectivityException) {
                        HelperUtils.pesan(mContext, t.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            this.hideDialog();
            e.printStackTrace();
            HelperUtils.pesan(getContext(), e.getMessage());
        }
    }

}