package com.coder.elaundry_apps.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.coder.elaundry_apps.R;

public class DetailLaundry extends AppCompatActivity {
    private TextView mNamaLaundry,mAlamatLaundry;
    private Button mCuci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laundry);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            setTitle("Detail Laundry");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        this.dataInit();
        Intent iin= getIntent();
        Bundle extra = iin.getExtras();
        if(extra != null) {
            String namaLaundry = extra.getString("namaLaundry","");
            String alamatLaundry = extra.getString("alamatLaundry","");
            String idLaundry = extra.getString("idLaundry","");
            mNamaLaundry.setText(namaLaundry);
            mAlamatLaundry.setText(alamatLaundry);

            mCuci.setOnClickListener(v -> {
                Intent intent = new Intent(DetailLaundry.this, OrderLaundry.class);
                intent.putExtra("idLaundry", idLaundry);
                intent.putExtra("namaLaundry", namaLaundry);
                intent.putExtra("alamatLaundry", alamatLaundry);
                startActivity(intent);
            });
        }

    }

    private void dataInit() {
        mNamaLaundry = findViewById(R.id.text_nama_laundry);
        mAlamatLaundry = findViewById(R.id.txt_alamat);
        mCuci = findViewById(R.id.btn_cuci);
    }
}