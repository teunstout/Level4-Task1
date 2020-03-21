package com.example.shoppinglistkotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @ColumnInfo(name = "item")
    val item: String,
    @ColumnInfo(name = "quantity")
    val howMany: Number,

    // zet hem niet boven aan dan moet je 3 values invoeren
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)