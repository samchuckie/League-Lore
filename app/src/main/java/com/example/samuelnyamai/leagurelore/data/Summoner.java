package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Summoner {
    public Summoner(List<ChampionsPlayed> championsPlayedList, List<SummonerRankedInfo> summonerRankedInfoList) {
        this.championsPlayedList = championsPlayedList;
        this.summonerRankedInfoList = summonerRankedInfoList;
    }

    @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("accountId")
        @Expose
        private String accountId;
        @SerializedName("puuid")
        @Expose
        private String puuid;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("profileIconId")
        @Expose
        private Integer profileIconId;

//        @SerializedName("revisionDate")
//        @Expose
//        private Integer revisionDate;

        @SerializedName("summonerLevel")
        @Expose
        private Integer summonerLevel;

        private List<ChampionsPlayed> championsPlayedList;

        private List<SummonerRankedInfo> summonerRankedInfoList;

    public List<ChampionsPlayed> getChampionsPlayedList() {
        return championsPlayedList;
    }

    public void setChampionsPlayedList(List<ChampionsPlayed> championsPlayedList) {
        this.championsPlayedList = championsPlayedList;
    }

    public List<SummonerRankedInfo> getSummonerRankedInfoList() {
        return summonerRankedInfoList;
    }

    public void setSummonerRankedInfoList(List<SummonerRankedInfo> summonerRankedInfoList) {
        this.summonerRankedInfoList = summonerRankedInfoList;
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getPuuid() {
            return puuid;
        }

        public void setPuuid(String puuid) {
            this.puuid = puuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getProfileIconId() {
            return profileIconId;
        }

        public void setProfileIconId(Integer profileIconId) {
            this.profileIconId = profileIconId;
        }

//        public Integer getRevisionDate() {
//            return revisionDate;
//        }
//
//        public void setRevisionDate(Integer revisionDate) {
//            this.revisionDate = revisionDate;
//        }

        public Integer getSummonerLevel() {
            return summonerLevel;
        }

        public void setSummonerLevel(Integer summonerLevel) {
            this.summonerLevel = summonerLevel;
        }


}
