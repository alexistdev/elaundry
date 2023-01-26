package com.coder.elaundry_apps.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("user_id")
    private final String idUser;

    @SerializedName("role")
    private final String role;

    public LoginModel(String idUser, String role) {
        this.idUser = idUser;
        this.role = role;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getRole() {
        return role;
    }
}
