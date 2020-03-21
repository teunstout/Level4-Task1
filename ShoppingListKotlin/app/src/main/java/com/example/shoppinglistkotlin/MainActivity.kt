package com.example.shoppinglistkotlin

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistkotlin.Database.ShoppingListRoomRepository

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    var items = arrayListOf<Item>()
    var itemAdapter = ItemAdapter(items)
    private lateinit var itemRepository: ShoppingListRoomRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        itemRepository = ShoppingListRoomRepository(this)
        getShoppingListFromDatabase()

        fab.setOnClickListener {addItem()}
        initView()
    }

    private fun initView() {
        rvItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        rvItems.adapter = itemAdapter
    }

    private fun addItem() {
        val newItem = Item(inWhatToBuy.text.toString(), inHowMany.text.toString().toInt())
        items.add(newItem)
        inHowMany.text?.clear()
        inWhatToBuy.text?.clear()
        itemAdapter.notifyDataSetChanged()
    }

private fun getShoppingListFromDatabase(){
    CoroutineScope(Dispatchers.Main).launch {
        val items = withContext(Dispatchers.IO){
            itemRepository.getAllProducts()
        }
        this@MainActivity.items.clear()
        this@MainActivity.items.addAll(items)
        this@MainActivity.itemAdapter.notifyDataSetChanged()
    }
}

}
