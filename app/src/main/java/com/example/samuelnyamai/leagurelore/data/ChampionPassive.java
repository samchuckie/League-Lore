package com.example.samuelnyamai.leagurelore.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ChampionPassive implements Parcelable {
    @SerializedName("name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("image")
    private ChampionImage championImage;
    public ChampionImage getChampionImage() {
        return championImage;
    }
    public void setChampionImage(ChampionImage championImage) {
        this.championImage = championImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.championImage, flags);
    }

    public ChampionPassive() {
    }

    protected ChampionPassive(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.championImage = in.readParcelable(ChampionImage.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChampionPassive> CREATOR = new Parcelable.Creator<ChampionPassive>() {
        @Override
        public ChampionPassive createFromParcel(Parcel source) {
            return new ChampionPassive(source);
        }

        @Override
        public ChampionPassive[] newArray(int size) {
            return new ChampionPassive[size];
        }
    };
}
