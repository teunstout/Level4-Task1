package com.example.shoppinglistkotlin.Database

import android.content.Context
import com.example.shoppinglistkotlin.Item

class ShoppingListRoomRepository(context: Context) {
    // init de itemDao
    private val itemDao: ItemDao  =
        ShoppingListRoomDatabase.getDatabase(context)!!.productDao()

    suspend fun getAllProducts(): List<Item> = itemDao.getAllItems()

    suspend fun insertProduct(item: Item) = itemDao.insertItem(item)

    suspend fun deleteProduct(item: Item) = itemDao.deleteItem(item)

    suspend fun deleteAllProducts() = itemDao.deleteAllItems()
}