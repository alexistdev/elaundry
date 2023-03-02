package com.coder.elaundry_apps.fragmentstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.AkunModel;
import com.coder.elaundry_apps.model.OrderModel;
import com.coder.elaundry_apps.ui.Login;
import com.coder.elaundry_apps.utils.HelperUtils;
import com.coder.elaundry_apps.utils.SessionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AkunStore extends Fragment {
    private ProgressBar progressBar;
    private Button mLogout,mSave;
    private EditText mNama,mPhone,mLatitude,mLongitude,mAlamat;

    public AkunStore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_akun_store, container, false);
        this.dataInit(mview);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionUtils.logout(requireContext());
                Intent intent = new Intent(getActivity(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                if(getActivity()!= null){
                    getActivity().finish();
                }
            }
        });
        this.setData(getContext());
        return mview;
    }

    private void dataInit(View mview) {
        mLogout = mview.findViewById(R.id.btn_logout);
        mNama = mview.findViewById(R.id.txt_nama);
        mPhone = mview.findViewById(R.id.txt_phone);
        mLatitude = mview.findViewById(R.id.txt_latitude);
        mLongitude = mview.findViewById(R.id.txt_longitude);
        mAlamat = mview.findViewById(R.id.txt_alamat);
        progressBar = mview.findViewById(R.id.progressBar);
        mSave = mview.findViewById(R.id.btn_tambah2);
        this.hideDialog();
    }

    private void setData(Context mContext){
        this.showDialog();
        try{
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                    Constants.KEY_USER, Context.MODE_PRIVATE);
            String userId = sharedPreferences.getString("idUser", "");
            Call<AkunModel> call= APIService.Factory.create(mContext).getAkun(userId);
            call.enqueue(new Callback<AkunModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<AkunModel> call, Response<AkunModel> response) {
                    hideDialog();
                    if(response.body() != null){
                        mNama.setText(response.body().getNamaLaundry());
                        mPhone.setText(response.body().getPhone());
                        mLatitude.setText(response.body().getLatitude());
                        mLongitude.setText(response.body().getLongitude());
                        mAlamat.setText(response.body().getAlamat());
                    }

                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<AkunModel> call, Throwable t) {
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

    private void doSave(){
        String nama = mNama.getText().toString();
        String phone = mPhone.getText().toString();
        String latitude = mLatitude.getText().toString();
        String longitude = mLongitude.getText().toString();
        String alamat = mAlamat.getText().toString();
        if(nama.length() == 0 && phone.length() == 0 && latitude.length() == 0 && longitude.length() == 0 && alamat.length() == 0){
            HelperUtils.pesan(getContext(),"Data tidak mengalami perubahan");
        } else {
            HelperUtils.pesan(getContext(),"Data berhasil disimpan");
        }
    }

    private void showDialog(){
        if(progressBar.getVisibility() ==  View.INVISIBLE){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideDialog(){
        if(progressBar.getVisibility() ==  View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}