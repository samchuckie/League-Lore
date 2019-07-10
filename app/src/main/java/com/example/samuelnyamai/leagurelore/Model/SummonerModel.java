package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.LeagueRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.LoginInterface;
import com.example.samuelnyamai.leagurelore.data.Summoner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.API_KEY;

public class SummonerModel {
    public static MutableLiveData<Summoner> getLogininstance(String server, String username){
        MutableLiveData<Summoner> summonerMutableLiveData = new MutableLiveData<>();
        LoginInterface loginInterface = LeagueRetro.getLeagueInstanceServers(server).create(LoginInterface.class);
        //LoginInterface loginInterface = LeagueRetro.getLeagueInstanceServers().create(LoginInterface.class);
        Call<Summoner> summonerCall= loginInterface.getPersonData(username,API_KEY);
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
//{
//        "profileIconId": 3879,
//        "name": "charliesdemon",
//        "puuid": "Qr64KexebqWSG_l8ZjeaeD9sbetcTc0aZz9NaMWd0WvT_PbxpvCLCj-DbQH9nzpZmGdjU5Bd7-b8sA",
//        "summonerLevel": 166,
//        "accountId": "cVe5B3V2qt7PUcQhK49GRKTwuwMLOGOqN5SNUX9DR2BngD8",
//        "id": "3ckvH8FwV3ZFIbGsJGDI5XhUjnFM_A_IjcFAFgz5FvwJEww",
//        "revisionDate": 1553489876000
//        }