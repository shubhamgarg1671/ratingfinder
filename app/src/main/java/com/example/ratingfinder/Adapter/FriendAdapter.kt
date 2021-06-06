package com.example.ratingfinder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ratingfinder.R

class FriendAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<FriendViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val currentItem = items[position]
        holder.friend.text = currentItem
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val friend : TextView = itemView.findViewById(R.id.friend)
}
