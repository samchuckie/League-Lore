package com.example.samuelnyamai.leagurelore.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.samuelnyamai.leagurelore.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_ICON_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.PNG_IMAGE_EXTENSION;

public class ChampionPLayedAdapter extends RecyclerView.Adapter <ChampionPLayedAdapter.ViewHolder>{
    private List<String> championsPlayedList = new ArrayList<>();

    @NonNull
    @Override
    public ChampionPLayedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.champions_played , viewGroup ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionPLayedAdapter.ViewHolder viewHolder, int i) {
        String icon_path = CHAMPION_ICON_BASE_URL + championsPlayedList.get(i)+ PNG_IMAGE_EXTENSION;
        Picasso.get().load(icon_path).into(viewHolder.championplayedicon_iv);
    }

    @Override
    public int getItemCount() {
        if(championsPlayedList.size()==0){ return 0;}
        return championsPlayedList.size();
    }

    public void setChampionsPlayedlist(List<String> championsPlayedList) {
        this.championsPlayedList = championsPlayedList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView championplayedicon_iv;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            championplayedicon_iv = itemView.findViewById(R.id.championplayedicon_iv);
        }
    }
}
