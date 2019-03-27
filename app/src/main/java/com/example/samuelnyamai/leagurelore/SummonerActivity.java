package com.example.samuelnyamai.leagurelore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionsPlayedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.RankedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampioMasteryInt;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.RankedInterface;
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

public class SummonerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);
        //Specific_ChampionModel specific_championModel = new Specific_ChampionModel();
        RankedInterface rankedInterface = RankedRetro.getRankedInfoInstance().create(RankedInterface.class);
        ChampioMasteryInt championsInterface = ChampionsPlayedRetro.getAllChampionMasteryInstance().create(ChampioMasteryInt.class);
        Observable<List<SummonerRankedInfo>> listCall =  rankedInterface.
                getsummoneranked("3ckvH8FwV3ZFIbGsJGDI5XhUjnFM_A_IjcFAFgz5FvwJEww",
                        "RGAPI-8507538e-bb9b-4b56-866c-f7145927471b")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());;
        Observable<List<ChampionsPlayed>> listCaller = championsInterface.
                getchampionsplayed("3ckvH8FwV3ZFIbGsJGDI5XhUjnFM_A_IjcFAFgz5FvwJEww",
                        "RGAPI-8507538e-bb9b-4b56-866c-f7145927471b")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

            Observable<Summoner> summonerObservable =  Observable.zip(listCall, listCaller, new BiFunction<List<SummonerRankedInfo>, List<ChampionsPlayed>, Summoner>() {
                @Override
                public Summoner apply(List<SummonerRankedInfo> summonerRankedInfos, List<ChampionsPlayed> championsPlayeds) throws Exception {
                    Log.e("sam","The observable is called ");

                   // Summoner summoner=apply(summonerRankedInfos,championsPlayeds);
                    Log.e("sam","The champion id is " + summonerRankedInfos.get(0).getLeagueName());
                   // Log.e("sam","The champion ranked name is " +summoner.getSummonerRankedInfoList().get(0).getLeagueName());
                    return null;
                }
            });
            summonerObservable.subscribe(new Observer<Summoner>() {
                @Override
                public void onSubscribe(Disposable disposable) {

                }

                @Override
                public void onNext(Summoner summoner) {
                    Log.e("sam","The champion id is " +summoner.getChampionsPlayedList().get(0).getChampionId());
                    Log.e("sam","The champion ranked name is " +summoner.getSummonerRankedInfoList().get(0).getLeagueName());
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

