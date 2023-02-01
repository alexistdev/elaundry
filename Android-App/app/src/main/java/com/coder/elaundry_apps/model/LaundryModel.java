package com.coder.elaundry_apps.model;

import com.google.gson.annotations.SerializedName;

public class LaundryModel {

    @SerializedName("id")
    private final String idLaundry;

    @SerializedName("nama_laundry")
    private final String namaLaundry;

    @SerializedName("phone")
    private final String phone;

    @SerializedName("alamat")
    private final String alamat;

    @SerializedName("longitude")
    private final String longitude;

    @SerializedName("latitude")
    private final String latitude;

    public LaundryModel(String idLaundry, String namaLaundry, String phone, String alamat, String longitude, String latitude) {
        this.idLaundry = idLaundry;
        this.namaLaundry = namaLaundry;
        this.phone = phone;
        this.alamat = alamat;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public String getPhone() {
        return phone;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
