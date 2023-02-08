package com.coder.elaundry_apps.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.utils.HelperUtils;

public class Register extends AppCompatActivity {
    private EditText mNama,mPhone,mEmail,mPassword;
    private ProgressBar progressBar;
    private Button mDaftar;
    private TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.initData();
        mLogin.setOnClickListener(v -> redirecTo());
        doRegister();
    }

    private void doRegister(){
        String nama = mNama.getText().toString();
        String phone = mPhone.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if (nama.trim().length() > 0 && phone.trim().length() > 0 && email.trim().length() > 0  && password.trim().length() > 0) {
            //this.dosave();
            redirecTo();
            HelperUtils.pesan(getApplicationContext(), "Anda berhasil registrasi!");
        } else {
            HelperUtils.pesan(getApplicationContext(), "Semua kolom harus diisi!");
        }
    }

    private void initData() {
        mNama = findViewById(R.id.txt_nama);
        mPhone = findViewById(R.id.txt_phone);
        mEmail = findViewById(R.id.txt_email);
        mPassword = findViewById(R.id.txt_password);
        mDaftar = findViewById(R.id.btn_daftar);
        mLogin = findViewById(R.id.txt_login);
        progressBar = findViewById(R.id.progressBar);
        this.hideDialog();
    }

    private void redirecTo() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
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
}