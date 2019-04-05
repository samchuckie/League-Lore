package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.samuelnyamai.leagurelore.Model.SpecificSummonerModel;
import com.example.samuelnyamai.leagurelore.data.Summoner;

import java.util.List;

public class Specific_SummonerViewModel extends AndroidViewModel {
    private LiveData<Summoner> summonerLiveData;
    private LiveData<String> backdrop_image;
    private LiveData<List<String>> championsPlayedList;

    public Specific_SummonerViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Summoner> getSummonerLiveData() {
        return SpecificSummonerModel.getSummonerLiveData();
    }

    public LiveData<String> getBackdrop_image(Context context, int champid) {
        return SpecificSummonerModel.getChampionbackdrop(context,champid);
    }

    public LiveData<List<String>> getChampionsPlayedList(Context context, List<String> champsplayed) {
        return SpecificSummonerModel.getChampionplayedList(context , champsplayed);
    }
    public void getData(String username,String server){
        SpecificSummonerModel.getData(username ,server);

    }
}
