package com.example.samuelnyamai.leagurelore.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampionsInterface;
import com.example.samuelnyamai.leagurelore.data.AllChampions;
import com.example.samuelnyamai.leagurelore.data.Datas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChampionModel {
    private static MutableLiveData<AllChampions> allChampionsMutableLiveData =new MutableLiveData<>();

    public static MutableLiveData<AllChampions> getAllChampionsMutableLiveData() {
        ChampionsInterface championsInterface = ChampionRetro.getAllChampionInstance().create(ChampionsInterface.class);
        Call<AllChampions> allChampionsCall = championsInterface.getAllChampions();
        allChampionsCall.enqueue(new Callback<AllChampions>() {
            @Override
            public void onResponse(Call<AllChampions> call, Response<AllChampions> response) {
                Log.e("sam","Call is "+ call.request().url().toString());
                Log.e("sam" , "Version is " + response.body().getVersion());
                Log.e("sam","Type is "  + response.body().getType());
                Log.e("sam","Making a call all time");
                String data = response.body().getDatas().getPoppy().getBlurb();
                Datas datas=response.body().getDatas();
                for (Method m: datas.getClass().getMethods())
                {
                    try {
                        if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                            final Object r = m.invoke(datas);
                            Log.e("sam","Reflected method is " + r.getClass().getSuperclass());
                        }


                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                Log.e("sam","Blurb is " + data);
                allChampionsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AllChampions> call, Throwable t) {
                Log.e("sam" , "Error occured is " + t.getMessage());
                //allChampionsMutableLiveData.setValue(null);

            }
        });
        return allChampionsMutableLiveData;
    }
}
