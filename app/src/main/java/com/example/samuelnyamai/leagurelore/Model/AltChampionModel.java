package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampionsInterface;
import com.example.samuelnyamai.leagurelore.Room.ChampionsDatabase;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltChampionModel {
    private static MutableLiveData<ChampionDetails> listMutableLiveDatas =new MutableLiveData<>();
    private static MutableLiveData<ChampionDetails> individualLivedata = new MutableLiveData<>();
    private Context context;
    private static Gson gson = new Gson();
    public AltChampionModel(Context context) {
        this.context = context;
    }
    public MutableLiveData<ChampionDetails> getIndividualLivedata() {return individualLivedata;}
    public MutableLiveData<ChampionDetails> getListMutableLiveDatas() {return listMutableLiveDatas;}
    public LiveData<ChampionDetails> getchampsInstance(Context context,String key) {
        return ChampionsDatabase.getChampionDatabseInstance(context).championsDAO().getSpecificChampions(key);
    }

    private Callback<ResponseBody> call =new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Log.e("sam","The url call is " + call.request().url());
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject data = jsonObject.getJSONObject("data");
                Iterator<String> keys = data.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    JSONObject datas = data.getJSONObject(key);
                    ChampionDetails champion = gson.fromJson(datas.toString(), ChampionDetails.class);
                    AllChampAsync.insertChampion(context,champion);
                    Log.e("sam" , "Champion is " + champion.getName());
                    listMutableLiveDatas.setValue(champion);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {}
    };

    private Callback<ResponseBody> callIndividual =new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Log.e("sam","The url call is " + call.request().url());
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject data = jsonObject.getJSONObject("data");
                Iterator<String> keys = data.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    JSONObject datas = data.getJSONObject(key);
                    ChampionDetails champion = gson.fromJson(datas.toString(), ChampionDetails.class);
                    AllChampAsync.insertChampion(context,champion);
                    Log.e("sam" , "Champion is " + champion.getName());
                    individualLivedata.setValue(champion);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {}
    };
    public void getListMutableLiveData() {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        Call<ResponseBody> allChampionsCall = championsInterface.getAlternativeChampions();
        allChampionsCall.enqueue(call);
    }
    public void getListMutableLiveData(String champion) {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        Call<ResponseBody> allChampionsCall = championsInterface.getSpecificChampion(champion);
        allChampionsCall.enqueue(callIndividual);
    }
}