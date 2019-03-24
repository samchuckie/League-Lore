package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Champion_details {
     @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private ChampionImage championImage;

    @SerializedName("skins")
    private List<ChampSkin> champImagesList;

    @SerializedName("lore")
    private String lore;

    @SerializedName("blurb")
    private String blurb;

    @SerializedName("spells")
    private Spells spells;

    public ChampionImage getChampionImage() {
        return championImage;
    }

    public void setChampionImage(ChampionImage championImage) {
        this.championImage = championImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<ChampSkin> getChampImagesList() {
        return champImagesList;
    }

    public void setChampImagesList(List<ChampSkin> champImagesList) {
        this.champImagesList = champImagesList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
