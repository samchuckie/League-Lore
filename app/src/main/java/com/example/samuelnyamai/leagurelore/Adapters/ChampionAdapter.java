package com.example.samuelnyamai.leagurelore.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_ICON_BASE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.CHAMPION_LOADINGIMAGE_URL;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.JPG_IMAGE_EXTENSION;

// TODO USES LOADING FOR SMALL SCREEN AND SPLASH (LARGER) FOR TABLETS
// TODO CACHE DATA IF DATABASE AND RECYCLERVIEW IS SLOW
public class ChampionAdapter extends RecyclerView.Adapter<ChampionAdapter.ViewHolder> {
    private List<ChampionDetails> championDetailsList =  new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View champion_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.champion_item_alt ,viewGroup ,false);
        return new ViewHolder(champion_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.champion_name.setText(championDetailsList.get(i).getName());
        viewHolder.champion_blurb.setText(championDetailsList.get(i).getBlurb());
        viewHolder.champion_title.setText(championDetailsList.get(i).getTitle());
        StringBuilder icon_path = new StringBuilder(CHAMPION_ICON_BASE_URL).append(championDetailsList.
                get(i).getChampionImage().getFull());
        StringBuilder background_path = new StringBuilder(CHAMPION_LOADINGIMAGE_URL).append(championDetailsList.
                        get(i).getId()).append("_0").append(JPG_IMAGE_EXTENSION);
        //Log.e("sam" ,"Image path is" + background_path.toString());
        Picasso.get().load(icon_path.toString()).noFade().into(viewHolder.champion_image);
        Picasso.get().load(background_path.toString()).noFade().into(viewHolder.champion_background_image);
    }

    @Override
    public int getItemCount() {
        if(championDetailsList.size()==0){ return 0;}
        return championDetailsList.size();
    }

    public void addChampion(ChampionDetails observer) {
        championDetailsList.add(observer);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView champion_name ,champion_blurb,champion_title;
        ImageView champion_image,champion_background_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            champion_name =itemView.findViewById(R.id.champion_name_tv);
            champion_blurb = itemView.findViewById(R.id.champion_blurb_tv);
            champion_image =itemView.findViewById(R.id.champion_iv);
            champion_background_image =itemView.findViewById(R.id.champion_background_iv);
            champion_title =itemView.findViewById(R.id.champion_title_tv);

        }
    }
}
