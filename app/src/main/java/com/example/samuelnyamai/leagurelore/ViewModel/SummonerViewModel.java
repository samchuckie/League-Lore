package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Model.SummonerModel;
import com.example.samuelnyamai.leagurelore.data.Summoner;

public class SummonerViewModel extends AndroidViewModel {
    private String server;
    private String username;
    private LiveData<Summoner> summonerLiveData;
    public SummonerViewModel(@NonNull Application application) {
        super(application);

    }
    public void getDetails(String server,String username){
        this.server =server;
        this.username =username;
    }
    public LiveData<Summoner> getSummonerLiveData(){
        summonerLiveData= SummonerModel.getLogininstance(server,username);
        Log.e("sam", "Livedata is null");
        return summonerLiveData;
    }
}
