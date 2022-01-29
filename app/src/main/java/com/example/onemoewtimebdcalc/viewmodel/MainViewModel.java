package com.example.onemoewtimebdcalc.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Double> setData = new MutableLiveData<>();


    public void getData(Double result)
    {
        setData.setValue( result );
    }
}
