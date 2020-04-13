package com.coni.sportscalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_create_post.*
import org.json.JSONObject

class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        button_submit.setOnClickListener()
        {
            val post = Post(1, editText_postDescription.text.toString(), editText_postLocation.text.toString())
            Server.getInstance(this).createPost(post, successPostResponse)
        }
    }

    private val successPostResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("PostActivity", "NetworkResponse : ${response.toString()}")
        finish()
    }
}