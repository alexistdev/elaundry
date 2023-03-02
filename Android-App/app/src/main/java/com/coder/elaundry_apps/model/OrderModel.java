package com.coder.elaundry_apps.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class OrderModel {

    @SerializedName("id")
    private final String idOrder;

    @SerializedName("user_id")
    private final String idUser;

    @SerializedName("store_id")
    private final String idLaundry;


    @SerializedName("satuan")
    private final int satuan;

    @SerializedName("total")
    private final int total;

    @SerializedName("phone")
    private final String phone;


    @SerializedName("longitude")
    private final String longitude;

    @SerializedName("latitude")
    private final String latitude;

    @SerializedName("tgl_selesai")
    private final String tglSelesai;

    @SerializedName("status")
    private final String status;

    @SerializedName("created_at")
    private final String createdAt;

    @SerializedName("customer")
    @JsonAdapter(NamaCustomerDeserializer.class)
    private final String namaCustomer;

    public OrderModel(String idOrder, String idUser, String idLaundry, int satuan, int total, String phone, String longitude, String latitude, String tglSelesai, String status, String createdAt, String namaCustomer) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.idLaundry = idLaundry;
        this.satuan = satuan;
        this.total = total;
        this.phone = phone;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tglSelesai = tglSelesai;
        this.status = status;
        this.createdAt = createdAt;
        this.namaCustomer = namaCustomer;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public int getSatuan() {
        return satuan;
    }

    public int getTotal() {
        return total;
    }

    public String getPhone() {
        return phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }
}
