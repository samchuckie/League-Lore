package com.example.samuelnyamai.leagurelore.Fragments;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;import android.support.v7.widget.LinearLayoutManager;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.samuelnyamai.leagurelore.Adapters.ChampionAdapter;
import com.example.samuelnyamai.leagurelore.IndividualChamp;
import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.ViewModel.AllChampionsViewModel;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
import java.util.Objects;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.ID_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.IMAGE_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.KEY_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.NAME_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.TITLE_EXTRA;

// TODO SIDE PROJECT FOR MOVIEDB CHANGE THE WHOLE THING. FOR ACTORS USE DIALOG AS WITH CHAMPIONS HERE
// TODO SIDE PROJECT STILL USE A CONTEXT MENU FOR DELETING TO PREVENT DELETE_FRAG FROM CRASHING DUE TO LOST ACTIVITY

public class ChampionFragment extends Fragment implements ChampionAdapter.ItemClickedInterface {
    private AllChampionsViewModel allChampionsViewModel;
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() ,2);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        ChampionAdapter championAdapter = new ChampionAdapter(this);
        recyclerView.setAdapter(championAdapter);

        // Lambda replaced with method expression


        // TODO ADD TAGS TO A SET TO SEE ALL THE TAGS AVAILABLE

        allChampionsViewModel.getChampionList().observe(this, championDetails -> {
                    if (championDetails != null){
                        for (ChampionDetails champ:championDetails) {
                            championAdapter.addChampion(champ);
                        }
                    }
                    else {

                        //  TODO CHECK IF THERE IS INTERNET. THIS IS AFTER DATABASE IS NULL

                            allChampionsViewModel.getListMutableLiveData();

                        }
                });
        return view;
    }

    @Override
    public void clickeditem(ChampionDetails championDetails) {
        Intent indivial_champ = new Intent(getContext(), IndividualChamp.class);
        indivial_champ.putExtra(KEY_EXTRA,championDetails.getKey());
        indivial_champ.putExtra(NAME_EXTRA,championDetails.getName());
        indivial_champ.putExtra(TITLE_EXTRA,championDetails.getTitle());
        indivial_champ.putExtra(ID_EXTRA,championDetails.getId());
        indivial_champ.putExtra(IMAGE_EXTRA,championDetails.getChampionImage().getFull());
        startActivity(indivial_champ);
    }
}

// TODO CHECK CHAMP ONCREATE REUSE FRAG
