package com.coder.elaundry_apps.model;

import com.google.gson.annotations.SerializedName;

public class HistoryModel {


    @SerializedName("history_id")
    private final String idHistory;

//    @SerializedName("store.nama_laundry")
//    private final String namaToko;

    @SerializedName("satuan")
    private final String satuan;

    @SerializedName("total")
    private final String total;

    @SerializedName("created_at")
    private final String tanggal;

    @SerializedName("tgl_selesai")
    private final String tanggalSelesai;

    @SerializedName("status")
    private final String status;

    @SerializedName("store")
    private Store store;

    public HistoryModel(String idHistory, String satuan, String total, String tanggal, String tanggalSelesai, String status) {
        this.idHistory = idHistory;
        this.satuan = satuan;
        this.total = total;
        this.tanggal = tanggal;
        this.tanggalSelesai = tanggalSelesai;
        this.status = status;

    }

    public String getIdHistory() {
        return idHistory;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getTotal() {
        return total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }

    public Store getStore() {
        return store;
    }

    public class Store{
        @SerializedName("nama_laundry")
        public String namaStore;

        public Store(String namaStore) {
            this.namaStore = namaStore;
        }

        public String getNamaStore() {
            return namaStore;
        }

    }
}
