package com.example.ratingfinder

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class FriendList : AppCompatActivity() {
    companion object {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        val bundle = intent.extras
        if (bundle!=null) {
            val message: String? = bundle.getString("result")
            val platform: String? = bundle.getString("platform")
            val user: String? = bundle.getString("User")
            val url: String? = bundle.getString("ProfileURL")
            Log.e("FriendList Activity", "through add friend button")
        }
        else
        {
            Log.e("FriendList Activity", "just a friend list")
        }


            val filename = "myfile"
        val fileContents = "Hello world!"
        try {
            val outputStreamWriter: OutputStreamWriter = OutputStreamWriter(this.openFileOutput(filename, Context.MODE_PRIVATE))
            outputStreamWriter.write(fileContents);
            outputStreamWriter.close();
            }
        catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        var line: String = ""

        try {
            val inputStream: InputStream = this.openFileInput(filename)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                line = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e")
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }

        Log.e(filename, line)
    }
}