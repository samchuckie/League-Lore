package com.example.samuelnyamai.leagurelore.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.data.ChampionSpells;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_SPELL;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.ViewHolder> {
    private List<ChampionSpells> championSpells =  new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View champion_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.abilities_items ,viewGroup ,false);
        return new ViewHolder(champion_view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.abilityname_tv.setText(championSpells.get(i).getName());
        viewHolder.abilitydesc_tv.setText(championSpells.get(i).getDescription());
        Picasso.get().load(CHAMPION_SPELL +championSpells.get(i).getChampionImage().getFull()).into(viewHolder.ability_icon);
    }

    @Override
    public int getItemCount() {
        if(championSpells.size()==0){ return 0;}
        return championSpells.size();
    }

    public void setAbilities(List<ChampionSpells> championSpells) {
        this.championSpells =championSpells;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView abilityname_tv,abilitydesc_tv;
        ImageView ability_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ability_icon =itemView.findViewById(R.id.ability_icon);
            abilityname_tv =itemView.findViewById(R.id.abilityname_tv);
            abilitydesc_tv =itemView.findViewById(R.id.abilitydesc_tv);

        }
    }
}
