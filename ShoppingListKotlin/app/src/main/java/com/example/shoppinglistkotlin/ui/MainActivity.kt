package com.example.shoppinglistkotlin.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistkotlin.R
import com.example.shoppinglistkotlin.database.ShoppingListRepository
import com.example.shoppinglistkotlin.model.Item

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    var items = arrayListOf<Item>()
    var itemAdapter = ItemAdapter(items)
    private lateinit var itemRepository: ShoppingListRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        itemRepository = ShoppingListRepository(this)
        initView()
    }


    private fun initView() {
        rvItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvItems.adapter = itemAdapter
        createItemTouchHelper().attachToRecyclerView(rvItems)
        getShoppingListFromDatabase()
        fab.setOnClickListener { addItem() }
    }



    private fun deleteShoppingList() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                itemRepository.deleteAllProducts()
            }
            getShoppingListFromDatabase()
        }
    }


    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val productToDelete = items[position]
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        itemRepository.deleteProduct(productToDelete)
                    }
                    getShoppingListFromDatabase()
                }
            }
        }
        return ItemTouchHelper(callback)
    }


    private fun addItem() {
        // corountine is een soort light weighted threath
        CoroutineScope(Dispatchers.Main).launch {
            if (validateFields()) {
                val newItem = Item(
                    inWhatToBuy.text.toString(),
                    inHowMany.text.toString().toInt()
                )
                withContext(Dispatchers.IO) {
                    itemRepository.insertProduct(newItem)
                }

                inWhatToBuy.text?.clear()
                inHowMany.text?.clear()

                getShoppingListFromDatabase()
            }
        }
    }


    private fun validateFields(): Boolean {
        return if (inWhatToBuy.text.toString().isNotBlank() && inHowMany.text.toString().isNotBlank()) {
            true
        } else {
            Toast.makeText(
                this,
                R.string.fillInCorrectly, Toast.LENGTH_SHORT
            ).show()
            false
        }
    }


    private fun getShoppingListFromDatabase() {
        // corountine is een soort light weighted threath
        CoroutineScope(Dispatchers.Main).launch {
            val itemsDatabase: List<Item> = withContext(Dispatchers.IO) {
                itemRepository.getAllProducts()
            }

            this@MainActivity.items.clear()
            this@MainActivity.items.addAll(itemsDatabase)
            this@MainActivity.itemAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_shopping_list -> {
                deleteShoppingList()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}