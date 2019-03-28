package com.example.samuelnyamai.leagurelore.Room.TypeConvertors;

import android.arch.persistence.room.TypeConverter;

import com.example.samuelnyamai.leagurelore.data.ChampionSpells;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SpellConvertor {
    static Gson gson = new Gson();

    @TypeConverter
    public static ChampionSpells stringToSomeObjectList(String data) {
        if (data == null) {
            return null ;
        }

        Type listType = new TypeToken<ChampionSpells>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(ChampionSpells someObjects) {
        return gson.toJson(someObjects);
    }
}

