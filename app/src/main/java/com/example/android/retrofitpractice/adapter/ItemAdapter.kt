package com.example.android.retrofitpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.retrofitpractice.R
import com.example.android.retrofitpractice.model.Recipe

class ItemAdapter (private val context: Context, private val clickListener:OnItemClick)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var usersList: List<Recipe> = emptyList()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val recipeName: TextView = view.findViewById(R.id.tv_recipe_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = usersList[position]
        holder.recipeName.text = item.title

        //click action
        holder.itemView.setOnClickListener {
            clickListener.onItemClickListener(position)
        }

    }

    override fun getItemCount()= usersList.size

    interface OnItemClick {
        fun onItemClickListener (position: Int)
    }
}