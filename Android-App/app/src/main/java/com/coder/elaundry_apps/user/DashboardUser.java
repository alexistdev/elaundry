package com.coder.elaundry_apps.user;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.fragmentUser.HomeUser;
import com.coder.elaundry_apps.fragmentUser.LaundryUser;
import com.coder.elaundry_apps.fragmentUser.MessageUser;
import com.coder.elaundry_apps.utils.HelperUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardUser extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
        this.dataInit();
        loadFragment(new HomeUser());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment;
            if(id == R.id.inbox_menu){
                fragment = new MessageUser();
            } else if(id == R.id.laundry_menu){
                fragment = new LaundryUser();
            } else {
                fragment = new HomeUser();
            }
            return loadFragment(fragment);
        });


    }



    private void dataInit()
    {
        this.loadFragment(new HomeUser());
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