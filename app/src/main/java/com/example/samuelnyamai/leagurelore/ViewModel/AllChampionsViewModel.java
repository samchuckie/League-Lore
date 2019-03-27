package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Model.AltChampionModel;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;

public class AllChampionsViewModel extends AndroidViewModel {
    public AllChampionsViewModel(@NonNull Application application) {
        super(application);
        Log.e("sam","Creating a new viewmodel");
    }

    public MutableLiveData<ChampionDetails> getListMutableLiveData(String[] servers) {

         AltChampionModel.getListMutableLiveData();
         return AltChampionModel.getListMutableLiveDatas();
    }
}
