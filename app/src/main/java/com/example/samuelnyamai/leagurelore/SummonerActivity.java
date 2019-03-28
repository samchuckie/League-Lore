package com.example.samuelnyamai.leagurelore;

import android.arch.lifecycle.ViewModelProviders;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuelnyamai.leagurelore.Model.SpecificSummonerModel;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.ChampionsPlayedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.LeagueRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroClasses.RankedRetro;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.ChampioMasteryInt;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.LoginInterface;
import com.example.samuelnyamai.leagurelore.Network.RetroInterfaces.RankedInterface;
import com.example.samuelnyamai.leagurelore.ViewModel.Specific_SummonerViewModel;
import com.example.samuelnyamai.leagurelore.ViewModel.SummonerViewModel;
import com.example.samuelnyamai.leagurelore.data.ChampionsPlayed;
import com.example.samuelnyamai.leagurelore.data.Summoner;
import com.example.samuelnyamai.leagurelore.data.SummonerRankedInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.API_KEY;

public class SummonerActivity extends AppCompatActivity {
    TextView s_name,s_level,sr_name,fr_name,sr_points,fr_points;
    ImageView summoner_backdrop_iv, summoner_currenticon_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner);
        Specific_SummonerViewModel viewModeler = ViewModelProviders.of(this).get(Specific_SummonerViewModel.class);
        viewModeler.getSummonerLiveData().observe(this, this::updateUi);

    }

    private void updateUi(Summoner summoner) {

    }


}

