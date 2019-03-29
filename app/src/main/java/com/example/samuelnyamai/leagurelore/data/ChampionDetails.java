package com.example.samuelnyamai.leagurelore.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class ChampionDetails {
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @SerializedName("image")
    private ChampionImage championImage;
    public ChampionImage getChampionImage() {return championImage;}
    public void setChampionImage(ChampionImage championImage) { this.championImage = championImage;}

    @SerializedName("skins")
    private List<ChampionSkin> champImagesList;

    public List<ChampionSkin> getChampImagesList() {
        return champImagesList;
    }

    public void setChampImagesList(List<ChampionSkin> champImagesList) {
        this.champImagesList = champImagesList;
    }

    @SerializedName("championSpells")
    private ChampionSpells championSpells;
    public ChampionSpells getChampionSpells() {
        return championSpells;
    }
    public void setChampionSpells(ChampionSpells championSpells) {
        this.championSpells = championSpells;
    }

    @SerializedName("passive")
    private ChampionPassive championPassive;
    public ChampionPassive getChampionPassive() {
        return championPassive;
    }
    public void setChampionPassive(ChampionPassive championPassive) {
        this.championPassive = championPassive;
    }

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;
    @NonNull
    public String getId() {
        return id;
    }
    public void setId(@NonNull String id) {
        this.id = id;
    }

    @SerializedName("lore")
    private String lore;
    public String getLore() {return lore;}
    public void setLore(String lore) {this.lore = lore;}

    @ColumnInfo(name = "blurb")
    @SerializedName("blurb")
    private String blurb;
    public String getBlurb() {return blurb;}
    public void setBlurb(String blurb) {this.blurb = blurb;}

    @ColumnInfo(name = "key")
    @SerializedName("key")
    private String key;
    public String getKey() {return key;}
    public void setKey(String key) {this.key = key;}


    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

}
