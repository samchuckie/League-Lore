package com.example.samuelnyamai.leagurelore.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.samuelnyamai.leagurelore.Model.AltChampionModel;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;

public class IndividualViewModel extends AndroidViewModel {
    private LiveData<ChampionDetails> liveDatas ;
    private AltChampionModel altChampionModels;
    private String key;
    private String name;
    private String savedstate ;
    private MutableLiveData<String> visiblelive  = new MutableLiveData<>();
    public void setvisibility(String visible) {
        visiblelive.setValue(visible);
    }
    public MutableLiveData<String> getVisible() {
        return visiblelive;
    }



    public String getSavedstate() {return savedstate;}
    public void setSavedstate(String savedstate) {this.savedstate = savedstate;}

    public String getKey() {return key;}
    public void setKey(String key) {this.key = key;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public IndividualViewModel(@NonNull Application application) {
        super(application);
        Log.e("sam","Creating a new individual viewmodel");
        altChampionModels = new AltChampionModel(getApplication());
    }

    public LiveData<ChampionDetails> getListMutableLiveData() {
        if(liveDatas==null)
        {
            liveDatas = altChampionModels.getchampsInstance(getApplication() ,getKey());
            Log.e("sam" , "Retreiving NEW instance in the INDIVIDUAL");
        }
        Log.e("sam" , "Retreiving PREVIOUS instance IN THE INDIVIDUAL");
        return liveDatas;
    }
    public void callChampion(String champion){
        altChampionModels.getListMutableLiveData(getName());
    }


}
