package com.example.tokopulsa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tokopulsa.model.Pulsa;
import com.example.tokopulsa.model.PulsaResponse;
import com.example.tokopulsa.model.PulsasResponse;

import com.example.tokopulsa.networking.PulsaRepositories;

public class PulsaViewModel extends ViewModel {
    private MutableLiveData<PulsaResponse> mutableLiveData;
    private PulsaRepositories pulsaRepositories;
    private MutableLiveData<PulsasResponse> mutablePulsaLiveData;

    public void init(){
        if (mutableLiveData!=null){
            return;
        }
        pulsaRepositories = PulsaRepositories.getInstance();
        mutableLiveData=pulsaRepositories.getPulsa();
    }

    public LiveData<PulsaResponse> getPulsa(){
        return mutableLiveData;
    }

    public LiveData<PulsasResponse> buyPulsa(Pulsa pulsa){
        if (mutablePulsaLiveData==null){
            pulsaRepositories =PulsaRepositories.getInstance();
        }
        mutablePulsaLiveData=pulsaRepositories.postPulsa(pulsa);
        return mutablePulsaLiveData;
    }
}
