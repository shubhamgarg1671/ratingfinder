package com.example.ratingfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ratingfinder.model.Friend

class DisplayRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_rating)

        // Get the Intent that started this activity and extract the bundle
        val bundle = intent.extras
        if (bundle!=null)
        {
            val message: String? = bundle.getString("result")
            // Capture the layout's TextView and set the string as its text
            val textView = findViewById<TextView>(R.id.ratingOutput).apply {
                text = message
            }
            val platform: String? = bundle.getString("platform")
            val user: String? = bundle.getString("User")
            val url: String? = bundle.getString("ProfileURL")

            // storing ID of the button
            // in a variable
            val gotoProfile = findViewById<Button>(R.id.gotoProfile)
            val addFriend = findViewById<Button>(R.id.AddFriend)
            if (message?.get(0).toString() == "E")
            {
                gotoProfile.visibility = View.GONE
                addFriend.visibility = View.GONE
            }

            gotoProfile.setOnClickListener()
            {
                // displaying a toast message Please wait..
                Toast.makeText(this, R.string.please_wait, Toast.LENGTH_LONG).show()

                val httpIntent = Intent(Intent.ACTION_VIEW)
                httpIntent.data = Uri.parse(url)

                startActivity(httpIntent)
            }
            addFriend.setOnClickListener (){
                Toast.makeText(this, "Added as Friend", Toast.LENGTH_LONG).show()
                val intent = Intent(this, FriendList::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(intent)
            }
        }
    }
}