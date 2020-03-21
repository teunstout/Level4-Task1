package com.example.shoppinglistkotlin.database

import androidx.room.*
import com.example.shoppinglistkotlin.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemTable")
    suspend fun getAllItems(): List<Item>

    @Insert
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM ItemTable")
    suspend fun deleteAllItems()
}