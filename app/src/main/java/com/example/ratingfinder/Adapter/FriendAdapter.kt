package com.example.ratingfinder.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ratingfinder.R
import com.example.ratingfinder.model.Friend
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FriendAdapter(val items: ArrayList<Friend>) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val currentItem = items[position]
        holder.platform.text = currentItem.platformName
        holder.handleName.text = currentItem.HandleName
            holder.rating.text = currentItem.getUserRating()
            Log.e("holder","from adapter")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)

        val holder = FriendViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val platform : TextView = itemView.findViewById(R.id.friend_platform)
        val handleName : TextView = itemView.findViewById(R.id.friend_Handlename)
        val rating : TextView = itemView.findViewById(R.id.friend_Rating)

    //    lateinit var profile_url:String
    }
}
