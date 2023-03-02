package com.coder.elaundry_apps.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.APIError;
import com.coder.elaundry_apps.model.LoginModel;
import com.coder.elaundry_apps.store.DashboardStore;
import com.coder.elaundry_apps.user.DashboardUser;
import com.coder.elaundry_apps.utils.ErrorUtils;
import com.coder.elaundry_apps.utils.HelperUtils;
import com.coder.elaundry_apps.utils.SessionUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Login extends AppCompatActivity {
    private TextView mPasswordRecovery, mEmail, mPassword,mRegister;
    private Button mLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.checkSession();
        this.initData();
        mPasswordRecovery.setOnClickListener(v -> redirecTo(PasswordRecovery.class));
        mRegister.setOnClickListener(v -> redirecTo(Register.class));
        progressBar.setVisibility(View.INVISIBLE);
        mLogin.setOnClickListener(v -> doLogin());

    }

    private void doLogin() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if (email.trim().length() > 0 && password.trim().length() > 0) {
            if (!HelperUtils.cekEmail(email)) {
                HelperUtils.pesan(getApplicationContext(), "Masukkan email yang valid !");
            }
            this.checkLogin(email, password);
        } else {
            HelperUtils.pesan(getApplicationContext(), "Semua kolom harus diisi!");
        }
    }

    private void checkSession() {
        if (SessionUtils.isLoggedIn(this)) {
            SharedPreferences sharedPreferences = getApplication().getSharedPreferences(
                    Constants.KEY_USER, Context.MODE_PRIVATE);
            String role = sharedPreferences.getString("role", "");
            if (Objects.equals(role, "2")) {
                redirecTo(DashboardStore.class);
            } else if (Objects.equals(role, "3")) {
                redirecTo(DashboardUser.class);
            } else {
                redirecTo(Login.class);
            }
        }
    }

    private void checkLogin(String email, String password) {
        this.showDialog();
        try {
            Call<LoginModel> call = APIService.Factory.create(getApplicationContext()).loginUser(email, password);
            call.enqueue(new Callback<LoginModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    hideDialog();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            SessionUtils.login(getApplicationContext(), response.body().getIdUser(), response.body().getRole(),response.body().getLatitude(),response.body().getLongitude());
                            if (response.body().getRole().equals("3")) {
                                redirecTo(DashboardUser.class);
                            } else {
                                redirecTo(DashboardStore.class);
                            }
                        }
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        if (error != null) {
                            HelperUtils.pesan(getApplicationContext(), error.message());
                        }
                    }
                }

                @EverythingIsNonNull
                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    hideDialog();
                    if (t instanceof NoConnectivityException) {
                        HelperUtils.pesan(getApplicationContext(), t.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            this.hideDialog();
            e.printStackTrace();
            HelperUtils.pesan(getApplicationContext(), e.getMessage());
        }
    }

    private void redirecTo(Class<?> className) {
        Intent intent = new Intent(Login.this, className);
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

    private void initData() {
        mPasswordRecovery = findViewById(R.id.txt_password_recovery);
        mEmail = findViewById(R.id.txt_email);
        mPassword = findViewById(R.id.txt_password);
        mLogin = findViewById(R.id.btn_login);
        mRegister = findViewById(R.id.txt_daftar);
        progressBar = findViewById(R.id.progressBar);
    }

}