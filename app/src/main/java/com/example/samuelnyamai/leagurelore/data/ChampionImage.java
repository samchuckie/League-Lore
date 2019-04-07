package com.example.samuelnyamai.leagurelore.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ChampionImage implements Parcelable {
   @SerializedName("full")
   private String full;
    public String getFull() {
        return full;
    }
    public void setFull(String full) {
        this.full = full;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.full);
    }

    public ChampionImage() {
    }

    private ChampionImage(Parcel in) {
        this.full = in.readString();
    }

    public static final Parcelable.Creator<ChampionImage> CREATOR = new Parcelable.Creator<ChampionImage>() {
        @Override
        public ChampionImage createFromParcel(Parcel source) {
            return new ChampionImage(source);
        }

        @Override
        public ChampionImage[] newArray(int size) {
            return new ChampionImage[size];
        }
    };
}
