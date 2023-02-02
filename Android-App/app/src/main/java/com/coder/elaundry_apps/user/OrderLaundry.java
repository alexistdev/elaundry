package com.coder.elaundry_apps.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.APIError;
import com.coder.elaundry_apps.model.OrderModel;
import com.coder.elaundry_apps.utils.ErrorUtils;
import com.coder.elaundry_apps.utils.HelperUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class OrderLaundry extends AppCompatActivity {
    public String namaLaundry = null;
    public String alamatLaundry = null;
    public String idLaundry = null;
    private Button mJemput;
    private EditText mSatuan,mPhone;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_laundry);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            setTitle("Pesan Laundry");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        this.initData();
        Intent iin= getIntent();
        Bundle extra = iin.getExtras();
        if(extra != null) {
            namaLaundry = extra.getString("namaLaundry","");
            alamatLaundry = extra.getString("alamatLaundry","");
            idLaundry = extra.getString("idLaundry","");
        }
        if(idLaundry != null){
            mJemput.setOnClickListener(v -> setData(idLaundry));
        }
    }

    private void simpan(int idLaundry,int satuan, String phone){
        this.showDialog();
        try {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                    Constants.KEY_USER, Context.MODE_PRIVATE);
            String idUser = sharedPreferences.getString("idUser", "");


            Call<OrderModel> call = APIService.Factory.create(getApplicationContext()).jemput(idLaundry,idUser,satuan,phone);
            call.enqueue(new Callback<OrderModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                    hideDialog();
                    if(response.isSuccessful()) {
                        Intent intent = new Intent(OrderLaundry.this, DashboardUser.class);
                        startActivity(intent);
                        HelperUtils.pesan(getApplicationContext(),"Anda berhasil memesan layanan jemput, silahkan ditunggu!");
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        if(error != null){
                            HelperUtils.pesan(getApplicationContext(),error.message());
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<OrderModel> call, Throwable t) {
                    hideDialog();
                    if(t instanceof NoConnectivityException) {
                        HelperUtils.pesan(getApplicationContext(),t.getMessage());
                    }
                }
            });
        }catch (Exception e){
            this.hideDialog();
            e.printStackTrace();
            HelperUtils.pesan(getApplicationContext(),e.getMessage());
        }
    }

    private void setData(String idLaundry)
    {
        String satuan = mSatuan.getText().toString();
        String phone = mPhone.getText().toString();
        if(satuan.length() == 0 && phone.length() == 0){
            HelperUtils.pesan(getApplicationContext(),"Harus diisi semua");
        } else {
            if(isNumeric(satuan)){
                this.simpan(Integer.parseInt(idLaundry),Integer.parseInt(satuan),phone);
            }else{
                HelperUtils.pesan(getApplicationContext(),"Format kg harus berupa angka!");
            }
        }
    }

    private boolean isNumeric(String strNum){
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void initData(){
        mJemput = findViewById(R.id.btn_jemput);
        mSatuan = findViewById(R.id.txt_kg);
        mPhone = findViewById(R.id.txt_phone);
        progressBar = findViewById(R.id.progressBar);
        this.hideDialog();
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