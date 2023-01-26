package com.coder.elaundry_apps.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.coder.elaundry_apps.MainActivity;
import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.user.DashboardUser;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        int timeout = 3000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent homepage = new Intent(Welcome.this, DashboardUser.class);
                startActivity(homepage);
            }
        }, timeout);
    }
}