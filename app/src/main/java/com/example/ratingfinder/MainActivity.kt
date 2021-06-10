package com.example.ratingfinder

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ratingfinder.model.Friend
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner2)

        // storing ID of the button
        // in a variable
        val button = findViewById<Button>(R.id.Submit_button)

        // operations to be performed
        // when user tap on the button
        button.setOnClickListener()
        {
            // displaying a toast message Please wait..
            Toast.makeText(this, R.string.please_wait, Toast.LENGTH_LONG).show()

            val user = findViewById<EditText>(R.id.handle_name)

            val platformName: String = spinner.selectedItem.toString()

            val friend = Friend(platformName, user.text.toString())

            /*val url: String = "https://competitive-coding-api.herokuapp.com/api/$platformName/${user.text.toString()}"
            //val url: String = "https://competitive-coding-api.herokuapp.com/api/codechef/shubhamgarg16"
*/
            val url: String = friend.apiurl()

            val queue = Volley.newRequestQueue(this)

            var res: String = ""

            // Request a string response from the provided URL.
            val JSONObject = JsonObjectRequest(Request.Method.GET, url, null,
                    { response ->


                        if(response.has("rating")){
                            // succes
                            // Rating is found
                            res = "Current Rating is: ${response.optString("rating")}"


                        } else {
                            res = "Error: ${response.optString("details")}"

                            // It doesn't exist, do nothing
                        }

                        val bundle = Bundle()
                        bundle.putString("result", res)
                        bundle.putString("platform", platformName)
                        bundle.putString("User", user.text.toString())
                        bundle.putString("ProfileURL", friend.getProfileLink())

                        val intent = Intent(this, DisplayRatingActivity::class.java).apply {
                            putExtras(bundle)
                        }
                        startActivity(intent)

                        //Log.e("rating", response.getString("rating"))
                        //Toast.makeText(this, res, Toast.LENGTH_LONG).show()
                    },
                    { error ->
                        Log.e("Volley error", error.toString())
                        res = "Something went Wrong. Please try again"
                        Toast.makeText(this, res, Toast.LENGTH_LONG).show()
                    })
            // Add the request to the RequestQueue.
            queue.add(JSONObject)
        }

        //code for dropdown in spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
                R.array.website,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }
}