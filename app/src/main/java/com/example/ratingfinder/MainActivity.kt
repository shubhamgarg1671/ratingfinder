package com.example.ratingfinder

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*
import kotlin.reflect.typeOf


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

            val editText = findViewById<EditText>(R.id.handle_name)
            //Log.e("shub", editText.text.toString())
            val platformName: String = spinner.selectedItem.toString()
            //Log.e("shu", platformName)
            val url: String = "https://competitive-coding-api.herokuapp.com/api/$platformName/${editText.text.toString()}"
            //val url: String = "https://competitive-coding-api.herokuapp.com/api/codechef/shubhamgarg16"
            //Log.e("sh", url)
            val queue = Volley.newRequestQueue(this)

            var res: String = ""

            // Request a string response from the provided URL.
            val JSONObject = JsonObjectRequest(Request.Method.GET, url, null,
                    { response ->

                        // Display the first 500 characters of the response string.
                        res = "Current Rating is: ${response.getString("rating")}"
                        //Log.e("rating", response.getString("rating"))

                        Toast.makeText(this, res, Toast.LENGTH_LONG).show()
                    },
                    { res = "That didn't work!"
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