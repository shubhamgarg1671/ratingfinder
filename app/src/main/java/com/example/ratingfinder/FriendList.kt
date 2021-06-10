package com.example.ratingfinder

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ratingfinder.Adapter.FriendAdapter
import com.example.ratingfinder.model.Friend
import java.io.*

class FriendList : AppCompatActivity() {
    companion object {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        val sharedPref: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val friendArray = arrayListOf<Friend>()
        val bundle = intent.extras
        if (bundle!=null) {
            val message: String? = bundle.getString("result")
            val platform: String? = bundle.getString("platform")
            val user: String? = bundle.getString("User")
            val url: String? = bundle.getString("ProfileURL")
            Log.e("FriendList Activity", "through add friend button")
            val editor: SharedPreferences.Editor = sharedPref.edit()

            // Using current time stamp as key in sharedPreference
            val currentTime = System.nanoTime().toString()
            editor.putString(currentTime, "$platform $user")
            editor.apply()
        }
        else
        {
            Log.e("FriendList Activity", "just a friend list")
        }
        val allEntries: Map<String, *> = sharedPref.getAll()
        for ((key, value) in allEntries) {
            Log.e("map values", key + ": " + value.toString())
            val str = value.toString().split(" ").toTypedArray()
            Log.e("map values", str[0] + ": " + str[1])
        }
        val recyclerView = findViewById<RecyclerView>(R.id.friend_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val item = getdata()
        val adapter: FriendAdapter = FriendAdapter(item)
        recyclerView.adapter= adapter
    }
    private fun getdata(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 1)
        {
            list.add(" Coming Soon...")
        }
        return list
    }
}