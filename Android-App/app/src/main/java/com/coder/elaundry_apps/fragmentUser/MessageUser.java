package com.coder.elaundry_apps.fragmentUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.ui.Login;
import com.coder.elaundry_apps.utils.HelperUtils;
import com.coder.elaundry_apps.utils.SessionUtils;

public class MessageUser extends Fragment {
    private Button mLogout,mSimpan;


    public MessageUser() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_message_user, container, false);
        this.initData(mview);

        mLogout.setOnClickListener(v -> {
            SessionUtils.logout(requireContext());
            Intent intent = new Intent(getActivity(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            if(getActivity()!= null){
                getActivity().finish();
            }
        });
        mSimpan.setOnClickListener(v -> {
            HelperUtils.pesan(v.getContext(),"Data berhasil disimpan!");
        });

        return mview;
    }

    private void initData(View mview) {
        mLogout = mview.findViewById(R.id.btn_logout);
        mSimpan = mview.findViewById(R.id.btn_simpan);

    }

//    private void redirecTo(Class<?> className) {
//        Intent intent = new Intent(Login.this, className);
//        startActivity(intent);
//        finish();
//    }
}