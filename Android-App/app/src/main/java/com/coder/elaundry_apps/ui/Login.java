package com.coder.elaundry_apps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.coder.elaundry_apps.R;

public class Login extends AppCompatActivity {
    private TextView mPasswordRecovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initData();
        mPasswordRecovery.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, PasswordRecovery.class);
            startActivity(intent);
        });
    }

    public void initData(){
        mPasswordRecovery = findViewById(R.id.txt_password_recovery);
    }
}