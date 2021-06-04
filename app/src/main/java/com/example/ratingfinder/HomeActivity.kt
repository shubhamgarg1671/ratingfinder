package com.example.ratingfinder

import android.app.ActivityManager
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val findRating = findViewById<Button>(R.id.findRating)

        // operations to be performed
        // when user tap on the button
        findRating.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java).apply{}
            startActivity(intent)
        }

        val friendList = findViewById<Button>(R.id.Friend_list)

        // operations to be performed
        // when user tap on the button
        friendList.setOnClickListener() {
            val intent = Intent(this, FriendList::class.java).apply{}
            startActivity(intent)
        }


        // I had copied this line from stackoverflow
        // Since volley is used for api requests and volley uses cache memory for same requests sent multiple times
        // So I am trying to delete app deta here so that latest rating are fetched always.
        this.cacheDir.deleteRecursively()

    }
}