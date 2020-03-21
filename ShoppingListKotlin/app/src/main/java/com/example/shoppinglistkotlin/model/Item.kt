package com.example.shoppinglistkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemTable")
data class Item(
    @ColumnInfo(name = "item")
    val item: String,
    @ColumnInfo(name = "quantity")
    val howMany: Int,

    // zet hem niet boven aan dan moet je 3 values invoeren
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)