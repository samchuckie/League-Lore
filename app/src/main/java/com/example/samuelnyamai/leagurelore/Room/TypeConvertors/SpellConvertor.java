package com.example.samuelnyamai.leagurelore.Room.TypeConvertors;

import android.arch.persistence.room.TypeConverter;
import com.example.samuelnyamai.leagurelore.data.ChampionSpells;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class SpellConvertor {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<ChampionSpells> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ChampionSpells>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<ChampionSpells> someObjects) {
        return gson.toJson(someObjects);
    }
}
