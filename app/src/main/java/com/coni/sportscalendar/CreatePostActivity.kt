package com.coni.sportscalendar

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_create_post.*
import org.json.JSONObject
import java.util.*

class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        var date = Date()
        button_submit.setOnClickListener()
        {
            val post = Post(0,1,create_description.text.toString(), create_sport.text.toString(),create_location.text.toString())//,
                //Date(100,24,234,1,5,6))
            Server.getInstance(this).createPost(post, successPostResponse)
        }

        //https://www.youtube.com/watch?v=tEQgn3BtrCw
        //val timePicker = TimePickerDialog(this)

    }

    private val successPostResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("PostActivity", "NetworkResponse : ${response.toString()}")
        finish()
    }
}