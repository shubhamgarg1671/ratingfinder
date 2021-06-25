package com.example.ratingfinder

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ratingfinder.Adapter.FriendAdapter
import com.example.ratingfinder.model.Friend
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        val recyclerView = findViewById<RecyclerView>(R.id.friend_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = FriendAdapter(friendArray)
        recyclerView.adapter= adapter
        val allEntries: Map<String, *> = sharedPref.getAll()
        val numberOfFriends = allEntries.size
        var count = 0
        for ((key, value) in allEntries) {
            val str = value.toString().split(" ").toTypedArray()
            val getFriend = Friend(str[0], str[1])

            val queue = Volley.newRequestQueue(this)
            // Request a response from the provided URL.
            val JSONObject = JsonObjectRequest(
                Request.Method.GET,getFriend.apiurl() , null,
                { response ->
                    getFriend.user_Rating = response.optString("rating")
                    friendArray.add(getFriend)
                    count++
                    Log.e("Success volley",getFriend.user_Rating)
                    adapter.notifyDataSetChanged()
                    if (count == numberOfFriends) {
                        Log.e("FriendListActivity","$count == $numberOfFriends")
                    }
                },
                { error ->
                    Log.e("Volley error", error.toString())
                    count++
                    if (count == numberOfFriends) {
                        Log.e("FriendListActivity","all friends were not added displayed")
                    }
                }
            )
            queue.add(JSONObject)
        }
        Log.e("shubham","hello")

    }
}