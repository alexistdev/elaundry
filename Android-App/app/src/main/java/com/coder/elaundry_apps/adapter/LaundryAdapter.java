package com.coder.elaundry_apps.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.model.LaundryModel;
import com.coder.elaundry_apps.user.DetailLaundry;

import java.util.List;

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.MyLaundryHolder>{
    List<LaundryModel> mLaundryList;

    public LaundryAdapter(List<LaundryModel> mLaundryList) {
        this.mLaundryList = mLaundryList;
    }

    @NonNull
    @Override
    public MyLaundryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_laundry, parent, false);
        LaundryAdapter.MyLaundryHolder holder;
        holder = new MyLaundryHolder(mView);
        return holder;
    }

    public static class MyLaundryHolder extends RecyclerView.ViewHolder {
        private final TextView mNamaLaundry,mAlamat;

        MyLaundryHolder(@NonNull View itemView) {
            super(itemView);
            mNamaLaundry = itemView.findViewById(R.id.txt_nama_laundry);
            mAlamat = itemView.findViewById(R.id.txt_alamat_laundry);
        }
    }

    @Override
    public void onBindViewHolder (@NonNull MyLaundryHolder holder,final int position){
        String namaLaundry = mLaundryList.get(position).getNamaLaundry();
        String idLaundry = mLaundryList.get(position).getIdLaundry();
        String alamatLaundry = mLaundryList.get(position).getAlamat();
        holder.mNamaLaundry.setText(namaLaundry);
        holder.mAlamat.setText(alamatLaundry);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), DetailLaundry.class);
                mIntent.putExtra("namaLaundry", namaLaundry);
                mIntent.putExtra("idLaundry", idLaundry);
                mIntent.putExtra("alamatLaundry", alamatLaundry);
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mLaundryList.size();
    }

    public void replaceData(List<LaundryModel> laundryModels) {
        this.mLaundryList = laundryModels;
        notifyDataSetChanged();
    }
}
