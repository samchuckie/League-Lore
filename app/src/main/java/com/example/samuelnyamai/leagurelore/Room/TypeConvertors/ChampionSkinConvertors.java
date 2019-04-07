package com.example.samuelnyamai.leagurelore.Room.TypeConvertors;

import android.arch.persistence.room.TypeConverter;

import com.example.samuelnyamai.leagurelore.data.ChampionSkin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ChampionSkinConvertors {
     private static Gson gson = new Gson();
    @TypeConverter
    public static List<ChampionSkin> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ChampionSkin>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<ChampionSkin> someObjects) {
        return gson.toJson(someObjects);
    }
}
