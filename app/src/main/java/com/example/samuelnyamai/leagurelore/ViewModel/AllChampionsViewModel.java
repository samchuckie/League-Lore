package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Model.ChampionModel;
import com.example.samuelnyamai.leagurelore.data.AllChampions;

public class AllChampionsViewModel extends AndroidViewModel {
    public AllChampionsViewModel(@NonNull Application application) {
        super(application);
        Log.e("sam","Creating a new viewmodel");
    }

    public MutableLiveData<AllChampions> getAllChampionsMutableLiveData(String[] servers) {

        return ChampionModel.getAllChampionsMutableLiveData(servers);
    }
}
