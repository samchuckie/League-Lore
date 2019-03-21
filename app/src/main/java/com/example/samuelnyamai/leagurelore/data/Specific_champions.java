package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specific_champions {
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

}
