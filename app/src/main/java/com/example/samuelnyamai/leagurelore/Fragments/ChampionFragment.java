package com.example.samuelnyamai.leagurelore.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

public class ChampionFragment extends Fragment implements ChampionAdapter.ItemClickedInterface {
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
        ChampionAdapter championAdapter = new ChampionAdapter(this);
        recyclerView.setAdapter(championAdapter);

        // Lambda replaced with method expression

        // TODO MAKE CALL TO AllChampAsync
        allChampionsViewModel.getChampionList().observe(this, championDetails -> {
                    if (championDetails.size()!=0){
                        for (ChampionDetails champ:championDetails) {
                           // Log.e("sam", "championdetail is " + champ.getName());
                            championAdapter.addChampion(champ);
                        }
                    }
                    else {
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
