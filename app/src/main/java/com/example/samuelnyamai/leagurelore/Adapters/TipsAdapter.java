package com.example.samuelnyamai.leagurelore.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.samuelnyamai.leagurelore.R;
import java.util.ArrayList;
import java.util.List;

public class TipsAdapter  extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    private List<String> alltips = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View champion_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tips_items ,viewGroup ,false);
        return new ViewHolder(champion_view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsAdapter.ViewHolder viewHolder, int i) {
        String tip = String.valueOf(i+1) + ". " +alltips.get(i);
        viewHolder.tips_tv.setText(tip);
    }

    @Override
    public int getItemCount() {
        if(alltips.size()==0){
            return 0;
        }
        return alltips.size();}

    public void setTips(List<String> alltips) {
        this.alltips = alltips;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tips_tv ;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tips_tv = itemView.findViewById(R.id.tips_tv);
        }
    }
}
