package com.coder.elaundry_apps.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.model.HistoryModel;
import com.coder.elaundry_apps.user.DetailLaundry;
import com.coder.elaundry_apps.user.DetailOrderan;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHistoryHolder>{
    List<HistoryModel> mHistoryList;

    public HistoryAdapter(List<HistoryModel> mHistoryList) {
        this.mHistoryList = mHistoryList;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_history, parent, false);
        HistoryAdapter.MyHistoryHolder holder;
        holder = new HistoryAdapter.MyHistoryHolder(mView);
        return holder;
    }

    public static class MyHistoryHolder extends RecyclerView.ViewHolder {
        private final TextView mIDOrder,mNamaToko,mTanggal,mStatus;

        MyHistoryHolder(@NonNull View itemView) {
            super(itemView);
            mIDOrder = itemView.findViewById(R.id.txt_id);
            mNamaToko = itemView.findViewById(R.id.txt_nama_toko);
            mTanggal = itemView.findViewById(R.id.txt_tanggal);
            mStatus = itemView.findViewById(R.id.txt_status);
        }
    }

    @Override
    public void onBindViewHolder (@NonNull HistoryAdapter.MyHistoryHolder holder, final int position){
        String idHistory = mHistoryList.get(position).getIdHistory();
        String toko = mHistoryList.get(position).getStore().getNamaStore();
        String tanggalSelesai = mHistoryList.get(position).getTanggalSelesai();
        String pesan = mHistoryList.get(position).getStatus();
        holder.mIDOrder.setText("#ORDERID :"+idHistory);
        holder.mNamaToko.setText(toko);
        holder.mTanggal.setText(tanggalSelesai);
        holder.mStatus.setText(pesan);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), DetailOrderan.class);
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mHistoryList.size();
    }

    public void replaceData(List<HistoryModel> historyModelList) {
        this.mHistoryList = historyModelList;
        notifyDataSetChanged();
    }
}
