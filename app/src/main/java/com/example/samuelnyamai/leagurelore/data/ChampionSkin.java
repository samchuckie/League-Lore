package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.SerializedName;
// TODO TRY AND INCLUDE CHROMAS
public class ChampionSkin {
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
