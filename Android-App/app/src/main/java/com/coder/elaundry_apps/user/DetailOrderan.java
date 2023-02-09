package com.coder.elaundry_apps.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.ui.MapsActivityUser;
import com.coder.elaundry_apps.utils.HelperUtils;

import java.util.Objects;

public class DetailOrderan extends AppCompatActivity {
    private Button mJemput,mStatus;
    private TextView mBerat,mTotal,mTanggalSelesai,mStatusOrder;

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
            if (Objects.equals(role, "2")) {
                mJemput.setVisibility(View.VISIBLE);
                mStatus.setVisibility(View.INVISIBLE);
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

    }
}