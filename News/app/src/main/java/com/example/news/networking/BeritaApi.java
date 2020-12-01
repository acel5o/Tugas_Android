package com.example.news.networking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.example.news.model.BeritaResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;

import com.example.news.model.Berita;
import com.example.news.model.BeritasResponse;



    public interface BeritaApi {
        @GET("berita")
        Call<BeritasResponse> getBeritasList(@Query("page") String page,
                                               @Query("limit") String limit);

        @POST("berita")
        Call<BeritaResponse> postBerita(@Body Berita body);
    }

