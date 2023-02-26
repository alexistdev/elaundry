package com.coder.elaundry_apps.fragmentstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coder.elaundry_apps.R;


public class LaundryStore extends Fragment {



    public LaundryStore() {
        // Required empty public constructor
    }


    public static LaundryStore newInstance(String param1, String param2) {
        LaundryStore fragment = new LaundryStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laundry_store, container, false);
    }
}