package com.example.ratingfinder.model

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

    fun apiurl(): String {
        return "https://competitive-coding-api.herokuapp.com/api/$platformName/${HandleName}"
    }
}