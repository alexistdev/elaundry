package com.coder.elaundry_apps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.model.LaundryModel;
import com.coder.elaundry_apps.model.OrderModel;
import com.coder.elaundry_apps.user.DetailOrderan;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder>{
    private List<OrderModel> mOrderList;
    private final Context context;

    public OrderAdapter(List<OrderModel> mOrderList, Context context) {
        this.mOrderList = mOrderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.single_list_order, parent, false);
        OrderAdapter.MyHolder holder;
        holder = new MyHolder(mView);
        return holder;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private final TextView mID,mTotal,mTanggal,mStatus;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            mID = itemView.findViewById(R.id.txt_id);
            mTotal = itemView.findViewById(R.id.txt_harga);
            mTanggal = itemView.findViewById(R.id.txt_tanggal);
            mStatus = itemView.findViewById(R.id.txt_status);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyHolder holder, int position) {
        String idOrder = mOrderList.get(position).getIdOrder();
        int satuan = mOrderList.get(position).getSatuan();
        int harga = mOrderList.get(position).getTotal();
        String longitude = mOrderList.get(position).getLongitude();
        String latitude = mOrderList.get(position).getLatitude();
        String tanggal = mOrderList.get(position).getCreatedAt();
        String status = mOrderList.get(position).getStatus();
        String cidOrder = holder.itemView.getContext().getString(R.string.detail13, idOrder);
        String ciTotal = holder.itemView.getContext().getString(R.string.rupiah, String.valueOf(harga));
        holder.mID.setText(cidOrder);
        holder.mTotal.setText(ciTotal);
        holder.mTanggal.setText(tanggal);
        holder.mStatus.setText(status);
        holder.itemView.setOnClickListener(v -> {
            Intent mIntent = new Intent(v.getContext(), DetailOrderan.class);
            mIntent.putExtra("idHistory", idOrder);
            mIntent.putExtra("satuan", String.valueOf(satuan));
            mIntent.putExtra("total", ciTotal);
            mIntent.putExtra("tanggalSelesai", tanggal);
            mIntent.putExtra("status", status);
            mIntent.putExtra("longitude", longitude);
            mIntent.putExtra("latitude", latitude);
            v.getContext().startActivity(mIntent);
        });
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public void replaceData(List<OrderModel> orderModels) {
        this.mOrderList = orderModels;
        notifyDataSetChanged();
    }
}
