package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChampionDetails {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("key")
    private String key;

     @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private ChampionImage championImage;

    @SerializedName("skins")
    private List<ChampionSkin> champImagesList;

    @SerializedName("lore")
    private String lore;

    @SerializedName("blurb")
    private String blurb;

    @SerializedName("championSpells")
    private ChampionSpells championSpells;

    @SerializedName("passive")
    private ChampionPassive championPassive;

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public ChampionSpells getChampionSpells() {
        return championSpells;
    }

    public void setChampionSpells(ChampionSpells championSpells) {
        this.championSpells = championSpells;
    }

    public ChampionPassive getChampionPassive() {
        return championPassive;
    }

    public void setChampionPassive(ChampionPassive championPassive) {
        this.championPassive = championPassive;
    }

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

    public List<ChampionSkin> getChampImagesList() {
        return champImagesList;
    }

    public void setChampImagesList(List<ChampionSkin> champImagesList) {
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
