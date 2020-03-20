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

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_layout.*

class MainActivity : AppCompatActivity() {
    var items = arrayListOf<Item>()
    var itemAdapter = ItemAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        items.add(Item("Test", 4))
        fab.setOnClickListener {addItem()}

        initView()
    }

    private fun initView() {
        rvItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        rvItems.adapter = itemAdapter
    }

    private fun addItem() {
        val newItem = Item(inWhatToBuy.text.toString(), inHowMany.text.toString().toFloat())
        items.add(newItem)
        inHowMany.text?.clear()
        inWhatToBuy.text?.clear()
        itemAdapter.notifyDataSetChanged()
    }



}
