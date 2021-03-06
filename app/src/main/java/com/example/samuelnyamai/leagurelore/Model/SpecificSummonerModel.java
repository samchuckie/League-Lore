package com.example.samuelnyamai.leagurelore.Model;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionsPlayedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.LeagueRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.RankedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampioMasteryInt;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.LoginInterface;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.RankedInterface;
import com.example.samuelnyamai.leagurelore.Room.ChampionsDatabase;
import com.example.samuelnyamai.leagurelore.data.ChampionsPlayed;
import com.example.samuelnyamai.leagurelore.data.Summoner;
import com.example.samuelnyamai.leagurelore.data.SummonerRankedInfo;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.API_KEY;


public class SpecificSummonerModel {
    private static MutableLiveData<Summoner> summonerLiveData;
    public static LiveData<String> getChampionbackdrop(Context context, int champid) {
        return ChampionsDatabase.getChampionDatabseInstance(context).championsDAO().getChampionID(String.valueOf(champid));
    }
    public static LiveData<List<String>> getChampionplayedList(Context context, List<String> championplayedlist) {
        return ChampionsDatabase.getChampionDatabseInstance(context).championsDAO().getListChampionsname(championplayedlist);
    }

    public static MutableLiveData<Summoner> getSummonerLiveData() {
        return summonerLiveData;
    }

    private static Summoner searchsummoner;
    public static void getData(String username, String server){
        summonerLiveData = new MutableLiveData<>();
        searchsummoner = new Summoner();

        //LoginInterface loginInterface = LeagueRetro.getLeagueInstanceServers(server).create(LoginInterface.class);
        LoginInterface loginInterface = LeagueRetro.getLeagueInstanceServers("EUW").create(LoginInterface.class);
        Call<Summoner> summonerCall = loginInterface.getPersonData(username,API_KEY);
        summonerCall.enqueue(new Callback<Summoner>() {
            @Override
            public void onResponse(Call<Summoner> call, Response<Summoner> response) {
                Log.e("sam", "The url isSS " + call.request().url().toString());
                    searchsummoner = response.body();
                if (searchsummoner != null) {
                    getPUUD(searchsummoner.getId(),server);
                }
                else{
                    Log.e("sam","Summoner is null resposnse is " + response);
                }
            }
            @Override
            public void onFailure(Call<Summoner> call, Throwable t) {
                Log.e("sam", "Error found is " + t.getMessage());
                Log.e("sam", "The url isSA " + call.request().url().toString());

            }
        });
    }
    private static void getPUUD(String puuid,String server) {
        Log.e("sam","Ranked info");

        RankedInterface rankedInterface = RankedRetro.getRankedInfoInstance(server).create(RankedInterface.class);
        ChampioMasteryInt championsInterface = ChampionsPlayedRetro.getAllChampionMasteryInstance(server).create(ChampioMasteryInt.class);
        Observable<List<SummonerRankedInfo>> listCall = rankedInterface.
                getsummoneranked(puuid,API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<List<ChampionsPlayed>> listCaller = championsInterface.
                getchampionsplayed(puuid,API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Log.e("sam","Ranked info is still being observed");

        Observable<Summoner> summonerObservable = Observable.zip(listCall, listCaller, (summonerRankedInfos, championsPlayeds) -> {
                    if (summonerRankedInfos != null){
                        searchsummoner.setSummonerRankedInfoList(summonerRankedInfos);
                        Log.e("sam","Ranked info");
                    }
                    else {
                        searchsummoner.setSummonerRankedInfoList(null);
                        Log.e("sam","summerRanked info is blank ");
                    }
                    searchsummoner.setChampionsPlayedList(championsPlayeds);
                    return searchsummoner;
                });
        summonerObservable.subscribe(new Observer<Summoner>() {
            @Override
            public void onSubscribe(Disposable disposable) {}
            @Override
            public void onNext(Summoner summoner) {
                summonerLiveData.setValue(summoner);
                Log.e("sam" , "The summoner name is " + summoner.getName());
            }
            @Override
            public void onError(Throwable throwable) { }
            @Override
            public void onComplete() {}
        });
    }
}
