package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.SerializedName;

public class ChampionImage {
   @SerializedName("full")
   private String full;
    public String getFull() {
        return full;
    }
    public void setFull(String full) {
        this.full = full;
    }

}
