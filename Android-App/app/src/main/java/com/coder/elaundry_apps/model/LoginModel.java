package com.coder.elaundry_apps.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("user_id")
    private final String idUser;

    @SerializedName("role")
    private final String role;

    @SerializedName("latitude")
    private final String latitude;

    @SerializedName("longitude")
    private final String longitude;

    public LoginModel(String idUser, String role, String latitude, String longitude) {
        this.idUser = idUser;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getRole() {
        return role;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
