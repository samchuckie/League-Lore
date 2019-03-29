package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChampionsPlayed {
    @SerializedName("championId")
    @Expose
    private Integer championId;
    public Integer getChampionId() {
        return championId;
    }
    public void setChampionId(Integer championId) {
        this.championId = championId;
    }



    @SerializedName("summonerId")
    @Expose
    private String summonerId;
    public String getSummonerId() {
        return summonerId;
    }
    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    @SerializedName("championLevel")
    @Expose
    private Integer championLevel;
    public Integer getChampionLevel() {
        return championLevel;
    }
    public void setChampionLevel(Integer championLevel) {
        this.championLevel = championLevel;
    }


    @SerializedName("championPoints")
    @Expose
    private Integer championPoints;
    public Integer getChampionPoints() {
        return championPoints;
    }
    public void setChampionPoints(Integer championPoints) {
        this.championPoints = championPoints;
    }

    @SerializedName("lastPlayTime")
    @Expose
    private Double lastPlayTime;
    public Double getLastPlayTime() {
        return lastPlayTime;
    }
    public void setLastPlayTime(Double lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    @SerializedName("championPointsSinceLastLevel")
    @Expose
    private Integer championPointsSinceLastLevel;
    public Integer getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }
    public void setChampionPointsSinceLastLevel(Integer championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    @SerializedName("championPointsUntilNextLevel")
    @Expose
    private Integer championPointsUntilNextLevel;
    public Integer getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }
    public void setChampionPointsUntilNextLevel(Integer championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    @SerializedName("chestGranted")
    @Expose
    private Boolean chestGranted;
    public Boolean getChestGranted() {
        return chestGranted;
    }
    public void setChestGranted(Boolean chestGranted) {
        this.chestGranted = chestGranted;
    }


    @SerializedName("tokensEarned")
    @Expose
    private Integer tokensEarned;
    public Integer getTokensEarned() {
        return tokensEarned;
    }
    public void setTokensEarned(Integer tokensEarned) {
        this.tokensEarned = tokensEarned;
    }


}
