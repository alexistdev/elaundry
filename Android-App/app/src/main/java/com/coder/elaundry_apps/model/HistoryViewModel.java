package com.coder.elaundry_apps.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoryViewModel extends ViewModel {
    private final MutableLiveData<HistoryModel> uiState = new MutableLiveData(new HistoryModel(null,null,null,null,null,null));
    public LiveData<HistoryModel> getUiState(){
        return uiState;
    }
}
