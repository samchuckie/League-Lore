package com.example.samuelnyamai.leagurelore.data;

import com.example.samuelnyamai.leagurelore.Champions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllChampions {
    @SerializedName("version")
    @Expose
    private
    String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @SerializedName("type")
    @Expose
    private
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @SerializedName("data")
    @Expose
    private
    List<Specific_champions> data;

    public List<Specific_champions> getData() {
        return data;
    }

    public void setData(List<Specific_champions> data) {
        this.data = data;
    }
}
