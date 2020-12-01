package com.example.news.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.model.Berita;
import com.example.news.model.BeritaResponse;
import com.example.news.model.BeritasResponse;
import com.example.news.networking.BeritasRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritasResponse> mutableLiveData;
    private BeritasRepository beritasRepository;
    private MutableLiveData<BeritaResponse> mutableBeritaLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

    public LiveData<BeritasResponse> getBeritasRepository() {
        return mutableLiveData;
    }
    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritasRepository.getBeritas(page, limit);
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }


    public LiveData<BeritaResponse> postBeritaRepository(Berita nasabahPayload) {
        if (mutableBeritaLiveData == null) {
            beritasRepository = BeritasRepository.getInstance();
        }
        mutableBeritaLiveData = beritasRepository.postBerita(nasabahPayload);

        return mutableBeritaLiveData;
    }



}
