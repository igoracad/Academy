package com.example.academyday2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item groceryItem);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    /*
    //---------------- KOTLIN way of doing it ------------------
    @Query("SELECT * from item WHERE id = :itemId")
    Flow<Item> getItem(int itemId);
    */


    //---------------- JAVA way of doing it ------------------
    @Query("SELECT * from item WHERE id = :itemId")
    Item getItem(int itemId);

    @Query("SELECT * from item ORDER BY name ASC")
    Flow<List<Item>> getItems();

}
