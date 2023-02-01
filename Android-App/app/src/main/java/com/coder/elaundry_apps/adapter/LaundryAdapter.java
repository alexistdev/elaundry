package com.coder.elaundry_apps.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.model.LaundryModel;

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
        holder.mNamaLaundry.setText(mLaundryList.get(position).getNamaLaundry());
        holder.mAlamat.setText(mLaundryList.get(position).getAlamat());
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
