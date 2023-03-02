package com.coder.elaundry_apps.model;

import com.google.gson.annotations.SerializedName;

public class AkunModel {
    @SerializedName("nama_laundry")
    private final String namaLaundry;

    @SerializedName("phone")
    private final String phone;

    @SerializedName("alamat")
    private final String alamat;

    @SerializedName("latitude")
    private final String latitude;

    @SerializedName("longitude")
    private final String longitude;

    @SerializedName("status")
    private final String status;

    @SerializedName("message")
    private final String message;

    public AkunModel(String namaLaundry, String phone, String alamat, String latitude, String longitude, String status, String message) {
        this.namaLaundry = namaLaundry;
        this.phone = phone;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.message = message;
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
