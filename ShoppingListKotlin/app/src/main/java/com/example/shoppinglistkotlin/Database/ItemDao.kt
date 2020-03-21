package com.example.shoppinglistkotlin.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppinglistkotlin.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): ArrayList<Item>
    @Insert
    suspend fun insertItem(item: Item)
    @Delete
    suspend fun deleteItem(item: Item)
    @Query("DELETE FROM Item")
    suspend fun deleteAllItems()
}