package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.SerializedName;

public class ChampionPassive {
    @SerializedName("name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("image")
    private ChampionImage championImage;
    public ChampionImage getChampionImage() {
        return championImage;
    }
    public void setChampionImage(ChampionImage championImage) {
        this.championImage = championImage;
    }
}
