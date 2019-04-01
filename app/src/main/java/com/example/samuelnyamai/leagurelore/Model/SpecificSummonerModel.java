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
    public static void getData(){
        summonerLiveData = new MutableLiveData<>();
        searchsummoner = new Summoner();

        // TODO CHANGE THE SERVER CHOICE FROM MANUAL TO DYNAMIC

        LoginInterface loginInterface = LeagueRetro.getLeagueInstanceServers("EUW").create(LoginInterface.class);
        Call<Summoner> summonerCall = loginInterface.getPersonData("charliesdemon", API_KEY);
        summonerCall.enqueue(new Callback<Summoner>() {
            @Override
            public void onResponse(Call<Summoner> call, Response<Summoner> response) {
                Log.e("sam", "The url is " + call.request().url().toString());
                searchsummoner=response.body();
                getPUUD(response.body().getId());
            }

            @Override
            public void onFailure(Call<Summoner> call, Throwable t) {
                Log.e("sam", "Error found is " + t.getMessage());
            }
        });
    }
    private static void getPUUD(String puuid) {
        RankedInterface rankedInterface = RankedRetro.getRankedInfoInstance().create(RankedInterface.class);
        ChampioMasteryInt championsInterface = ChampionsPlayedRetro.getAllChampionMasteryInstance().create(ChampioMasteryInt.class);
        Observable<List<SummonerRankedInfo>> listCall = rankedInterface.
                getsummoneranked(puuid,
                        API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<List<ChampionsPlayed>> listCaller = championsInterface.
                getchampionsplayed(puuid,
                        API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<Summoner> summonerObservable = Observable.zip(listCall, listCaller,
                new BiFunction<List<SummonerRankedInfo>, List<ChampionsPlayed>, Summoner>() {
            @Override
            public Summoner apply(List<SummonerRankedInfo> summonerRankedInfos, List<ChampionsPlayed> championsPlayeds) throws Exception {
                Log.e("sam", "The champion id is " + summonerRankedInfos.get(0).getLeagueName());
                searchsummoner.setSummonerRankedInfoList(summonerRankedInfos);
                searchsummoner.setChampionsPlayedList(championsPlayeds);
                return searchsummoner;
            }
        });
        summonerObservable.subscribe(new Observer<Summoner>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Summoner summoner) {
                summonerLiveData.setValue(summoner);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
