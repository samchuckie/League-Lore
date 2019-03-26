package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummonerRankedInfo {

    @SerializedName("tier")
    @Expose
    private String tier;

    @SerializedName("summonerName")
    @Expose
    private String summonerName;

    @SerializedName("hotStreak")
    @Expose
    private Boolean hotStreak;

    @SerializedName("wins")
    @Expose
    private Integer wins;

    @SerializedName("veteran")
    @Expose
    private Boolean veteran;

    @SerializedName("losses")
    @Expose
    private Integer losses;

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("leagueName")
    @Expose
    private String leagueName;

    @SerializedName("inactive")
    @Expose
    private Boolean inactive;

    @SerializedName("freshBlood")
    @Expose
    private Boolean freshBlood;

    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("leagueId")
    @Expose
    private String leagueId;

    @SerializedName("queueType")
    @Expose
    private String queueType;

    @SerializedName("summonerId")
    @Expose
    private String summonerId;

    @SerializedName("leaguePoints")
    @Expose
    private Integer leaguePoints;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Boolean getHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(Boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Boolean getVeteran() {
        return veteran;
    }

    public void setVeteran(Boolean veteran) {
        this.veteran = veteran;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Boolean getFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(Boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }
}