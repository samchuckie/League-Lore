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
import android.widget.TextView;
import com.example.samuelnyamai.leagurelore.Adapters.TipsAdapter;
import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.ViewModel.IndividualViewModel;
import java.util.Objects;

public class LoreFragment extends Fragment {
    IndividualViewModel individualViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        individualViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(IndividualViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lore_fragment, container, false);
        TextView lore_tv;
        RecyclerView allies_rv , enemies_rv;
        lore_tv = view.findViewById(R.id.lore_tv);
        allies_rv = view.findViewById(R.id.allies_rv);
        enemies_rv = view.findViewById(R.id.enemies_rv);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManagerr= new LinearLayoutManager(getContext());
        allies_rv.setLayoutManager(linearLayoutManager);
        enemies_rv.setLayoutManager(linearLayoutManagerr);
        TipsAdapter allies = new TipsAdapter();
        TipsAdapter enemies = new TipsAdapter();
        allies_rv.setAdapter(allies);
        enemies_rv.setAdapter(enemies);
        individualViewModel.getListMutableLiveData().observe(this,observer->{

            if(observer!=null && observer.getAlltips()!=null){
                lore_tv.setText(observer.getLore());
                allies.setTips(observer.getAlltips());
                enemies.setTips(observer.getEnemytips());
                individualViewModel.setvisibility("visible");
            }
            else {
                individualViewModel.callChampion();
                Log.e("sam", "Champ tips is so null");
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        individualViewModel.setSavedstate("lore");
    }
}

// TODO CHECK IF DATABINDING CAN WORK WITH VIEWMODEL VARIABLE ON PROGBBAR