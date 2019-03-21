package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.LeagueRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.LoginInterface;
import com.example.samuelnyamai.leagurelore.data.Summoner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummonerModel {
    public static MutableLiveData<Summoner> getLogininstance(String server, String username){
        MutableLiveData<Summoner> summonerMutableLiveData = new MutableLiveData<>();
        LoginInterface loginInterface = LeagueRetro.getLeagueInstance(server).create(LoginInterface.class);
        Call<Summoner> summonerCall= loginInterface.getPersonData(username, "RGAPI-dd6ca9d8-a87e-40c3-84eb-454fa5521162");
        summonerCall.enqueue(new Callback<Summoner>() {
            @Override
            public void onResponse(Call<Summoner> call, Response<Summoner> response) {
                Log.e("sam", "The url is " + call.request().url().toString());
                summonerMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Summoner> call, Throwable t) {
                Log.e("sam","Error found is " + t.getMessage());
                summonerMutableLiveData.setValue(null);
            }
        });
        return summonerMutableLiveData;
    }
}
