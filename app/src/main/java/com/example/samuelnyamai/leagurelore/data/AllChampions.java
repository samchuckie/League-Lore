package com.example.samuelnyamai.leagurelore.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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


    //     https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit/34375405
    ///     https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit/34375405
    //  TODO HAVE ON CLASS WITH BASE WITH BASE PROPERTIES OF VERTSION AND STATS
    // SEARCH "get nested json object with gson using retrofit"


    public Datas getDatas() {
        return datas;
    }

    public void setData(Datas data) {
        this.datas = datas;
    }

    @SerializedName("data")
    @Expose
    private
    Datas datas;

}
