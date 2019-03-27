package com.example.samuelnyamai.leagurelore.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samuelnyamai.leagurelore.Adapters.ChampionAdapter;
import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.ViewModel.AllChampionsViewModel;

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
        String[] servers = getResources().getStringArray(R.array.champions);
        allChampionsViewModel.getListMutableLiveData(servers).observe(getViewLifecycleOwner(), championAdapter::addChampion);

        // Lambda replaced with method expression

//        ChampionsDatabase.getChampionDatabseInstance(getContext()).championsDAO().getAllChampions().
//                observe(this, new Observer<List<ChampionDetails>>() {
//                    @Override
//                    public void onChanged(@Nullable List<ChampionDetails> championDetails) {
//                        Log.e("sam", "The first champion name is " + championDetails.get(0).getName());
//                    }
//                });
        return view;
    }
}
