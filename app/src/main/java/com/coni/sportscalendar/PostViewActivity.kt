package com.coni.sportscalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_post_view.*

class PostViewActivity : AppCompatActivity() {

    lateinit var postData :Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)

        val username = intent.getStringExtra(TeamSearchActivity.POST_DATA)
        postData = Gson().fromJson(username, Post::class.java)

        textView_postView_description.text = "Description:  ${postData.description} "
        textView_postView_location.text = "Vieta:  ${postData.location} "
    }
}