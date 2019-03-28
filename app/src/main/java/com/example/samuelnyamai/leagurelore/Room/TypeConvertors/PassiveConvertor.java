package com.example.samuelnyamai.leagurelore.Room.TypeConvertors;

import android.arch.persistence.room.TypeConverter;

import com.example.samuelnyamai.leagurelore.data.ChampionPassive;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PassiveConvertor {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ChampionPassive stringToSomeObjectList(String data) {
        if (data == null) {
            return null ;
        }

        Type listType = new TypeToken<ChampionPassive>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(ChampionPassive someObjects) {
        return gson.toJson(someObjects);
    }
}

