//package com.example.samuelnyamai.leagurelore.Room;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.example.samuelnyamai.leagurelore.data.ChampionDetails;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.DATABASE_NAME;
//
//@Database(entities = {ChampionDetails.class}, version = 1)
//public abstract class ChampionsDatabase extends RoomDatabase {
//
//    //  TODO make singleton of ChampionDatabase instance
//    //  TODO FIRST CHECK IF REVISION_NUMBER HAS CHANGED IF YES MAKE A CALL AND CHANGE THE DETAILS OF USER AND UPDATE SHAREDPREFERENCE
//    //  TODO IF (INTERNET)->CHECK PATCH_NUMBER.
//    //  TODO IF (GREATER) MAKE A NETWORK CALL AND UPDATE THE DATABASE
//    //  TODO IF(EQUAL) -> MAKE A CALL TO THE DATABASE TO RETURN LIST OF CHAMPIONS
//
//    public static ChampionsDatabase getChampionDatabseInstance(Context context){
//        Log.e("sam" ,"An instance is being created");
//        return Room.databaseBuilder(context,ChampionsDatabase.class,DATABASE_NAME).build();
//
//    }
//    public abstract ChampionsDAO championsDAO();
//}
