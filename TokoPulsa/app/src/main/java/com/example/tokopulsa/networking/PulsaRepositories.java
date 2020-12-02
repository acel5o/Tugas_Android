package com.example.tokopulsa.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tokopulsa.model.Pulsa;
import com.example.tokopulsa.model.PulsaResponse;
import com.example.tokopulsa.model.PulsasResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaRepositories {
    private static PulsaRepositories pulsaRepositories;

    public static PulsaRepositories getInstance(){
        if (pulsaRepositories==null){
            pulsaRepositories=new PulsaRepositories();
        }
        return pulsaRepositories;
    }

    private PulsaApi pulsaApi;

    public PulsaRepositories(){
        pulsaApi=RetrofitService.createService(PulsaApi.class);
    }

    public MutableLiveData<PulsaResponse> getPulsa(){
        MutableLiveData<PulsaResponse> pulsaData = new MutableLiveData<>();
        pulsaApi.getPulsa()
                .enqueue(new Callback<PulsaResponse>() {
                    @Override
                    public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("LogGetPulsa: ",response.body().toString());
                            pulsaData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PulsaResponse> call, Throwable t) {
                        Log.v("LogErrorFetch: ",t.getMessage());
                        pulsaData.setValue(null);
                    }
                });
        return pulsaData;
    }

    public MutableLiveData<PulsasResponse> postPulsa(Pulsa Payload){
        MutableLiveData<PulsasResponse> pulsaData = new MutableLiveData<>();
        pulsaApi.buyPulsa(Payload).enqueue(new Callback<PulsasResponse>() {
            @Override
            public void onResponse(Call<PulsasResponse> call,
                                   Response<PulsasResponse> response) {
                if (response.isSuccessful()){
                    pulsaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PulsasResponse> call, Throwable t) {
                pulsaData.setValue(null);
            }
        });
        return pulsaData;
    }
}
