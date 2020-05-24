package com.coni.sportscalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_post_view.*
import org.json.JSONArray
import org.json.JSONObject

class PostViewActivity : AppCompatActivity() {

    lateinit var postData :Post
    var isJoined :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)

        val username = intent.getStringExtra(TeamSearchActivity.POST_DATA)
        postData = Gson().fromJson(username, Post::class.java)

        textView_postView_descriptionResult.text = "${postData.description} "
        textView_postView_locationResult.text = "${postData.location} "
        textView_postView_sportTypeResult.text = "${postData.sport}"
        textView_postView_creatorResult.text = "${postData.authorName}"
        textView_postView_timeResult.text = "${postData.startTime}"

        button_postView_join.setOnClickListener()
        {
            if(!isJoined)
            {
                Server.getInstance(this).joinPost(postData.id,successJoinPostResponse)

            }
            else
            {
                Server.getInstance(this).leavePost(postData.id,successLeavePostResponse)
            }
        }
    }

    private val successJoinPostResponse = Response.Listener <JSONObject>()
    {
        button_postView_join.setImageResource(R.mipmap.post_view_exit)
        isJoined = !isJoined
    }

    private val successLeavePostResponse = Response.Listener <JSONObject>()
    {
        button_postView_join.setImageResource(R.mipmap.post_view_join)
        isJoined = !isJoined
    }
}