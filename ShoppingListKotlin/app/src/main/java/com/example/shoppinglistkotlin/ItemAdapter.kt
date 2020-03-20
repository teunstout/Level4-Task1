package com.example.shoppinglistkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemAdapter(val items: List<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    inner class ViewHolder(textView: View): RecyclerView.ViewHolder(textView) {
        fun bind(item: Item){
            itemView.rvItemText.text = String.format("%d. %s", item.howMany, item.item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}