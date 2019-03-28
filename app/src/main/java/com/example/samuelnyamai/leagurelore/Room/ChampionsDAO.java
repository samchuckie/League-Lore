package com.example.samuelnyamai.leagurelore.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.samuelnyamai.leagurelore.data.ChampionDetails;

import java.util.List;

@Dao
public interface ChampionsDAO {
    @Query("SELECT * FROM ChampionDetails")
    LiveData<List<ChampionDetails>> getAllChampions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addChampion(ChampionDetails championDetails);

//    @Query("SELECT * FROM TestDB")
//    LiveData<List<TestDB>> getAllChampions();
}
