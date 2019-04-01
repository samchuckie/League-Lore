package com.example.samuelnyamai.leagurelore.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.example.samuelnyamai.leagurelore.Adapters.ChampionAdapter;
import com.example.samuelnyamai.leagurelore.Room.TypeConvertors.ChampionSkinConvertors;
import com.example.samuelnyamai.leagurelore.Room.TypeConvertors.ImageConvertor;
import com.example.samuelnyamai.leagurelore.Room.TypeConvertors.ListConvertor;
import com.example.samuelnyamai.leagurelore.Room.TypeConvertors.PassiveConvertor;
import com.example.samuelnyamai.leagurelore.Room.TypeConvertors.SpellConvertor;
import com.example.samuelnyamai.leagurelore.data.ChampionDetails;


import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.DATABASE_NAME;

@Database(entities = {ChampionDetails.class}, version = 4,exportSchema = false)
@TypeConverters({ChampionSkinConvertors.class , ImageConvertor.class, PassiveConvertor.class, SpellConvertor.class , ListConvertor.class})

public abstract class ChampionsDatabase extends RoomDatabase {

    //  TODO FIRST CHECK IF REVISION_NUMBER HAS CHANGED IF YES MAKE A CALL AND CHANGE THE DETAILS OF USER AND UPDATE SHAREDPREFERENCE
    //  TODO IF (INTERNET)->CHECK PATCH_NUMBER.
    //  TODO IF (GREATER) MAKE A NETWORK CALL AND UPDATE THE DATABASE
    //  TODO IF(EQUAL) -> MAKE A CALL TO THE DATABASE TO RETURN LIST OF CHAMPIONS
    private static ChampionsDatabase championsDatabase;

    public static ChampionsDatabase getChampionDatabseInstance(Context context){
        if(championsDatabase==null) {
            synchronized (ChampionsDatabase.class) {
                Log.e("sam", "An instance is being created");
                championsDatabase = Room.databaseBuilder(context, ChampionsDatabase.class, DATABASE_NAME).
                        fallbackToDestructiveMigration()
                        //.allowMainThreadQueries()
                        .build();
            }
        }
            return championsDatabase;
    }
    public abstract ChampionsDAO championsDAO();
}
