package com.example.ratingfinder.model

import android.content.Context
import android.media.Rating
import android.util.Log
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Friend (val platformName: String, val HandleName: String){
    fun getProfileLink() : String
    {
        if (platformName == "codeforces") {
            //https://codeforces.com/profile/shubham_garg16
            return "https://codeforces.com/profile/$HandleName"
        }
        else if (platformName == "codechef") {
            //https://codechef.com/users/shubhamgarg16
            return "https://codechef.com/users/$HandleName"
        }
        return ""
    }

    lateinit var user_Rating:String
    fun apiurl(): String {
        return "https://competitive-coding-api.herokuapp.com/api/$platformName/${HandleName}"
    }
    fun getUserRating() :String {

        return user_Rating
    }
}