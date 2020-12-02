package com.example.tokopulsa.networking;

import com.example.tokopulsa.model.Pulsa;
import com.example.tokopulsa.model.PulsaResponse;
import com.example.tokopulsa.model.PulsasResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PulsaApi {

    @GET("product")
    Call<PulsaResponse> getPulsa();

    @POST("pulsa")
    Call<PulsasResponse> buyPulsa(@Body Pulsa pulsa);

}
