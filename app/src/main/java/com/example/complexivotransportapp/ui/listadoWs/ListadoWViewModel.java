package com.example.complexivotransportapp.ui.listadoWs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListadoWViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListadoWViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}