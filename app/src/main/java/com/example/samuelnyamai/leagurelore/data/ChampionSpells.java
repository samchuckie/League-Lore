package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.SerializedName;

// TODO SPLIT THE SPELL ID TO REMOVE CHAMPION NAME FROM KEYBINDING
public class ChampionSpells {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private ChampionImage championImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChampionImage getChampionImage() {
        return championImage;
    }

    public void setChampionImage(ChampionImage championImage) {
        this.championImage = championImage;
    }
}
