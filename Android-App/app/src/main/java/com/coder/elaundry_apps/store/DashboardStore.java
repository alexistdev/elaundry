package com.coder.elaundry_apps.store;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.fragmentUser.HomeUser;
import com.coder.elaundry_apps.fragmentstore.AkunStore;
import com.coder.elaundry_apps.fragmentstore.HomeStore;
import com.coder.elaundry_apps.fragmentstore.LaundryStore;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardStore extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_store);
        this.dataInit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment;
            if(id == R.id.inbox_menu){
                fragment = new AkunStore();
            } else if(id == R.id.laundry_menu){
                fragment = new LaundryStore();
            } else {
                fragment = new HomeStore();
            }

            return loadFragment(fragment);
        });
    }

    private void dataInit()
    {
        this.loadFragment(new HomeStore());
        bottomNavigationView = findViewById(R.id.bottomMenu);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}