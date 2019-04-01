package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Model.AltChampionModel;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;

public class AllChampionsViewModel extends AndroidViewModel {
    MutableLiveData<ChampionDetails> liveData;
    public AllChampionsViewModel(@NonNull Application application) {
        super(application);
        Log.e("sam","Creating a new viewmodel");
    }

    public MutableLiveData<ChampionDetails> getListMutableLiveData(Context context) {
         if(liveData==null){
             AltChampionModel.getListMutableLiveData(context);
             liveData =AltChampionModel.getListMutableLiveDatas();
         }
         return liveData;
    }
    public MutableLiveData<ChampionDetails> getListMutableLiveData(Context context, String champion) {
        if(liveData==null)
        {
            AltChampionModel.getListMutableLiveData(context ,champion);
            liveData =AltChampionModel.getListMutableLiveDatas();
        }
        return liveData;
    }
}
