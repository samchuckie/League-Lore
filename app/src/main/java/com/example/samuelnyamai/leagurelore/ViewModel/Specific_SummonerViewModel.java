package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.samuelnyamai.leagurelore.Model.SpecificSummonerModel;
import com.example.samuelnyamai.leagurelore.data.Summoner;

public class Specific_SummonerViewModel extends AndroidViewModel {
    private LiveData<Summoner> summonerLiveData;
    public Specific_SummonerViewModel(@NonNull Application application) {
        super(application);
        SpecificSummonerModel.getData();
    }

    public LiveData<Summoner> getSummonerLiveData() {
        return SpecificSummonerModel.getSummonerLiveData();
    }
}
