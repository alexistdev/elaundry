package com.coder.elaundry_apps.model;

import com.google.gson.annotations.SerializedName;

public class OrderModel {

    @SerializedName("user_id")
    private final String idUser;

    @SerializedName("store_id")
    private final String idLaundry;


    @SerializedName("satuan")
    private final String satuan;

    @SerializedName("phone")
    private final String phone;

    public OrderModel(String idUser, String idLaundry, String satuan, String phone) {
        this.idUser = idUser;
        this.idLaundry = idLaundry;
        this.satuan = satuan;
        this.phone = phone;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getPhone() {
        return phone;
    }
}
