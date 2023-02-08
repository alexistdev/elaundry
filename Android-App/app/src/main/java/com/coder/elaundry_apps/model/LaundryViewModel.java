package com.coder.elaundry_apps.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LaundryViewModel extends ViewModel {
    private final MutableLiveData<LaundryModel> uiState = new MutableLiveData(new LaundryModel(null,null,null,null,null,null,null));

    public LiveData<LaundryModel> getUiState(){
        return uiState;
    }

}
