package com.example.shoppinglistkotlin.database

import android.content.Context
import com.example.shoppinglistkotlin.model.Item

class ShoppingListRepository(context: Context) {

    private var itemDao: ItemDao

    init {
        val database = ShoppingListRoomDatabase.getDatabase(context)
        itemDao = database!!.itemDao()
    }

    suspend fun getAllProducts(): List<Item> = itemDao.getAllItems()

    suspend fun insertProduct(item: Item) = itemDao.insertItem(item)

    suspend fun deleteProduct(item: Item) = itemDao.deleteItem(item)

    suspend fun deleteAllProducts() = itemDao.deleteAllItems()
}