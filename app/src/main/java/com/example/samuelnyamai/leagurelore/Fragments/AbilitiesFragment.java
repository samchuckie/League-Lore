package com.example.samuelnyamai.leagurelore.Fragments;

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
import android.widget.ImageView;
import android.widget.TextView;
import com.example.samuelnyamai.leagurelore.Adapters.AbilitiesAdapter;
import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.ViewModel.IndividualViewModel;
import com.example.samuelnyamai.leagurelore.data.ChampionPassive;
import com.squareup.picasso.Picasso;
import java.util.Objects;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_PASSIVE;

public class AbilitiesFragment extends Fragment {
    IndividualViewModel individualViewModel;
    TextView passive_tv,passivename_tv;
    ImageView passive_icon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        individualViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(IndividualViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.abilities_fragment, container, false);
        RecyclerView abilites_rv = view.findViewById(R.id.abilites_rv);
        passive_icon =view.findViewById(R.id.ability_icon);
        passivename_tv =view.findViewById(R.id.abilityname_tv);
        passive_tv =view.findViewById(R.id.abilitydesc_tv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        AbilitiesAdapter abilitiesAdapter = new AbilitiesAdapter();
        abilites_rv.setLayoutManager(linearLayoutManager);
        abilites_rv.setAdapter(abilitiesAdapter);
        individualViewModel.getListMutableLiveData().observe(this,observer-> {
            if(observer!=null && observer.getChampionSpells()!=null) {
                Log.e("sam", "The passive is " + observer.getChampionPassive().getDescription());
                Log.e("sam", "The spell is " + observer.getChampionSpells().get(0).getName());
                abilitiesAdapter.setAbilities(observer.getChampionSpells());
                updateUI(observer.getChampionPassive());
            }
        });

            return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        individualViewModel.setSavedstate("abilities");
    }


    private void updateUI(ChampionPassive championPassive) {
        Picasso.get().load(CHAMPION_PASSIVE + championPassive.getChampionImage().getFull()).into(passive_icon);
        Log.e("sam" , "The passive path is " + championPassive.getChampionImage().getFull());
        passive_tv.setText(championPassive.getDescription());
        passivename_tv.setText(championPassive.getName());
    }
}
// TODO USE ON FRAG INST