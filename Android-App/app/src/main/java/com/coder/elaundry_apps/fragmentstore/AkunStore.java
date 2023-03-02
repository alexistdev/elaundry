package com.coder.elaundry_apps.fragmentstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.ui.Login;
import com.coder.elaundry_apps.utils.SessionUtils;

public class AkunStore extends Fragment {

    private Button mLogout;

    public AkunStore() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_akun_store, container, false);
        this.dataInit(mview);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionUtils.logout(requireContext());
                Intent intent = new Intent(getActivity(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                if(getActivity()!= null){
                    getActivity().finish();
                }
            }
        });
        return mview;
    }

    private void dataInit(View mview) {

        mLogout = mview.findViewById(R.id.btn_logout);
    }

    private void doSave(){
//        String nama = mNama.getText().toString();
//        String pass = mPassword.getText().toString();
//        if(nama.length() == 0 && pass.length() == 0){
//            HelperUtils.pesan(getContext(),"Data tidak mengalami perubahan");
//        } else {
//            HelperUtils.pesan(getContext(),"Data berhasil disimpan");
//        }
    }
}