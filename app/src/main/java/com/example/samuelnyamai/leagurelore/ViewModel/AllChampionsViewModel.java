package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.samuelnyamai.leagurelore.Model.AllChampAsync;
import com.example.samuelnyamai.leagurelore.Model.AltChampionModel;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;

import java.util.List;

public class AllChampionsViewModel extends AndroidViewModel {
    private MutableLiveData<ChampionDetails> liveData;

    public LiveData<List<ChampionDetails>> getChampionList() {
        LiveData<List<ChampionDetails>> championList;
        championList= AllChampAsync.getchampsInstance(getApplication());
        if (championList==null){
            Log.e("sam", "The list is null");
            getListMutableLiveData();
        }


        //getListMutableLiveData();




        Log.e("sam" ,"The champion list is being fetched from database");
        return championList;
    }


    private AltChampionModel altChampionModel ;
    public AllChampionsViewModel(@NonNull Application application) {
        super(application);
        Log.e("sam","Creating a new all champions viewmodel");
        altChampionModel = new AltChampionModel(getApplication());
    }

    public MutableLiveData<ChampionDetails> getListMutableLiveData() {
         if(liveData==null){
             altChampionModel.getListMutableLiveData();
             liveData =altChampionModel.getListMutableLiveDatas();
         }
         return liveData;
    }
}
