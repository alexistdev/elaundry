package com.coder.elaundry_apps.response;

import com.coder.elaundry_apps.model.HistoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetHistory {
    @SerializedName("data")
    List<HistoryModel> daftarHistory;

    @SerializedName("message")
    final private String message;

    @SerializedName("status")
    final private Boolean status;

    public GetHistory(List<HistoryModel> daftarHistory, String message, Boolean status) {
        this.daftarHistory = daftarHistory;
        this.message = message;
        this.status = status;
    }

    public List<HistoryModel> getDaftarHistory() {
        return daftarHistory;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }
}
