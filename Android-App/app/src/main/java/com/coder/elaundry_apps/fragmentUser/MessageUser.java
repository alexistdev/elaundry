package com.coder.elaundry_apps.fragmentUser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.APIService;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.api.NoConnectivityException;
import com.coder.elaundry_apps.model.APIError;
import com.coder.elaundry_apps.model.LoginModel;
import com.coder.elaundry_apps.response.GetLaundry;
import com.coder.elaundry_apps.ui.Login;
import com.coder.elaundry_apps.utils.ErrorUtils;
import com.coder.elaundry_apps.utils.HelperUtils;
import com.coder.elaundry_apps.utils.SessionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MessageUser extends Fragment {
    private Button mLogout,mSimpan;
    private EditText mPass1,mPass2;


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
            doLogin();
        });

        return mview;
    }

    private void initData(View mview) {
        mLogout = mview.findViewById(R.id.btn_logout);
        mSimpan = mview.findViewById(R.id.btn_simpan);
        mPass1 = mview.findViewById(R.id.txt_password1);
        mPass2 = mview.findViewById(R.id.txt_password2);

    }

    private void doLogin(){
        String pass1 = mPass1.getText().toString();
        String pass2 = mPass2.getText().toString();
        if (pass1.trim().length() > 0 && pass2.trim().length() > 0) {
            if(!pass1.equals(pass2)){
                HelperUtils.pesan(getContext(), "Password tidak sama!");
            } else {
                this.doSave(pass1,getContext());
            }
        } else {
            HelperUtils.pesan(getContext(), "Semua kolom harus diisi!");
        }
    }

    private void doSave(String pass1,Context mContext)
    {
        try{
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences(
                    Constants.KEY_USER, Context.MODE_PRIVATE);
            String idUser = sharedPreferences.getString("idUser", null);
            if(idUser != null){
                Call<LoginModel> call = APIService.Factory.create(mContext).akunSetting(idUser, pass1);
                call.enqueue(new Callback<LoginModel>() {
                    @EverythingIsNonNull
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        if (response.isSuccessful()) {
                            HelperUtils.pesan(mContext,"Data berhasil diupdate");
                        } else {
                            APIError error = ErrorUtils.parseError(response);
                            if (error != null) {
                                HelperUtils.pesan(mContext, error.message());
                            }
                        }
                    }
                    @EverythingIsNonNull
                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        if (t instanceof NoConnectivityException) {
                            HelperUtils.pesan(mContext, t.getMessage());
                        }
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            HelperUtils.pesan(getContext(), e.getMessage());
        }
    }
}