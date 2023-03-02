package com.coder.elaundry_apps.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.OrderModel;
import com.coder.elaundry_apps.model.TitikOrder;
import com.coder.elaundry_apps.ui.MapsActivityUser;
import com.coder.elaundry_apps.utils.HelperUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class DetailOrderan extends AppCompatActivity {
    private Button mJemput,mStatus;
    private TextView mBerat,mTotal,mTanggalSelesai,mStatusOrder;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_orderan);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            setTitle("Detail Order");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        this.dataInit();
        Intent iin= getIntent();
        Bundle extra = iin.getExtras();
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(
                Constants.KEY_USER, Context.MODE_PRIVATE);
        String role = sharedPreferences.getString("role", "");
        if(extra != null) {
            String longitude = extra.getString("longitude","");
            String latitude = extra.getString("latitude","");
            if (Objects.equals(role, "2")) {
                String idOrder = extra.getString("idHistory","");
                mJemput.setVisibility(View.VISIBLE);
                mStatus.setVisibility(View.INVISIBLE);
                String idHistory = extra.getString("idHistory","");
                String satuan = extra.getString("satuan","");
                String harga = extra.getString("total","");
                String tanggalSelesai = extra.getString("tanggalSelesai","");
                String status = extra.getString("status","");
                String berat = getString(R.string.detail14, satuan);

                mBerat.setText(berat);
                mTotal.setText(harga);
                mTanggalSelesai.setText(tanggalSelesai);
                mStatusOrder.setText(status);
                mJemput.setOnClickListener(v -> {
                    jemput(idOrder,getApplicationContext(),latitude,longitude);
                });
            } else {
                String idHistory = extra.getString("idHistory","");
                String satuan = extra.getString("satuan","");
                String harga = extra.getString("total","");
                String tanggalSelesai = extra.getString("tanggalSelesai","");
                String status = extra.getString("status","");
                String berat = getString(R.string.detail14, satuan);
                String total = getString(R.string.detail15, harga);
                mBerat.setText(berat);
                mTotal.setText(total);
                mTanggalSelesai.setText(tanggalSelesai);
                mStatusOrder.setText(status);
                mStatus.setVisibility(View.VISIBLE);
                mJemput.setVisibility(View.INVISIBLE);
                mStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mIntent = new Intent(DetailOrderan.this, MapsActivityUser.class);
                        startActivity(mIntent);
                    }
                });


            }

        }

    }

    private void dataInit() {
        mJemput = findViewById(R.id.btn_jemput);
        mStatus = findViewById(R.id.btn_cek);
        mBerat = findViewById(R.id.txt_berat);
        mTotal = findViewById(R.id.txt_harga);
        mTanggalSelesai = findViewById(R.id.txt_tanggal);
        mStatusOrder = findViewById(R.id.txt_status);
        progressBar = findViewById(R.id.progressBar);
        this.hideDialog();
    }

    private void jemput(String idOrder, Context mContext,String latitude,String longitude){
        this.showDialog();
        try{
            Call<OrderModel> call= APIService.Factory.create(mContext).ubahStatus(Integer.parseInt(idOrder),1);
            call.enqueue(new Callback<OrderModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                    hideDialog();
                    Intent mIntent = new Intent(DetailOrderan.this, MapsActivityUser.class);
                    mIntent.putExtra("latitude", latitude);
                    mIntent.putExtra("longitude", longitude);
                    startActivity(mIntent);
                    HelperUtils.pesan(mContext,"Pesanan Berhasil anda proses");
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<OrderModel> call, Throwable t) {
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