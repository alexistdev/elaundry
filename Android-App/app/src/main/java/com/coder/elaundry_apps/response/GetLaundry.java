package com.coder.elaundry_apps.response;

import com.coder.elaundry_apps.model.LaundryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLaundry {
    @SerializedName("data")
    List<LaundryModel> daftarLaundry;

    @SerializedName("message")
    final private String message;

    @SerializedName("status")
    final private Boolean status;

    public GetLaundry(List<LaundryModel> daftarLaundry, String message, Boolean status) {
        this.daftarLaundry = daftarLaundry;
        this.message = message;
        this.status = status;
    }

    public List<LaundryModel> getDaftarLaundry() {
        return daftarLaundry;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }
}
