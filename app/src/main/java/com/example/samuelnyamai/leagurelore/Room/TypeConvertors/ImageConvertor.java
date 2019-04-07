package com.example.samuelnyamai.leagurelore.Room.TypeConvertors;

import android.arch.persistence.room.TypeConverter;

import com.example.samuelnyamai.leagurelore.data.ChampionImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ImageConvertor {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ChampionImage stringToSomeObjectList(String data) {
        if (data == null) {
            return null ;
        }

        Type listType = new TypeToken<ChampionImage>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(ChampionImage someObjects) {
        return gson.toJson(someObjects);
    }
}
