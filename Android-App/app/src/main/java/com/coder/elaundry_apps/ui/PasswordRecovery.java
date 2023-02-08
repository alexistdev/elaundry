package com.coder.elaundry_apps.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.coder.elaundry_apps.R;

public class PasswordRecovery extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        TextView mBack = findViewById(R.id.txt_back);
        mBack.setOnClickListener(v -> redirecTo());
    }

    private void redirecTo() {
        Intent intent = new Intent(PasswordRecovery.this, Login.class);
        startActivity(intent);
        finish();
    }
}