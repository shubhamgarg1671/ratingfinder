package com.example.ratingfinder

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
            val user: String? = bundle.getString("user")
            var url: String = ""
            if (platform == "codeforces")
            {
                Log.e("Display Rating Activity", platform)
            }
            else if (platform == "codechef")
            {
                Log.e("Display Rating Activity", platform)
            }
        }
    }
}