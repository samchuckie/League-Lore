package com.example.samuelnyamai.leagurelore.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samuelnyamai.leagurelore.Adapters.ChampionAdapter;
import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.Room.ChampionsDatabase;
import com.example.samuelnyamai.leagurelore.ViewModel.AllChampionsViewModel;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;

import java.util.List;
import java.util.Objects;

    // TODO SIDE PROJECT FOR MOVIEDB CHANGE THE WHOLE THING. FOR ACTORS USE DIALOG AS WITH CHAMPIONS HERE

public class ChampionFragment extends Fragment {
    AllChampionsViewModel allChampionsViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allChampionsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(AllChampionsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.champion_fragment,container ,false);
        RecyclerView recyclerView = view.findViewById(R.id.champion_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ChampionAdapter championAdapter = new ChampionAdapter();
        recyclerView.setAdapter(championAdapter);
        //allChampionsViewModel.getListMutableLiveData(getContext()).observe(getViewLifecycleOwner(), championAdapter::addChampion);

        // Lambda replaced with method expression

        // TODO MAKE CALL TO AllChampAsync
        ChampionsDatabase.getChampionDatabseInstance(getContext()).championsDAO().getAllChampions().
                observe(this, championDetails -> {
                    if (championDetails.size()!=0){
                        for (ChampionDetails champ:championDetails) {
                            Log.e("sam", "championdetail is " + champ.getName());
                            championAdapter.addChampion(champ);

                        }
                    }
                });
        return view;
    }
}
