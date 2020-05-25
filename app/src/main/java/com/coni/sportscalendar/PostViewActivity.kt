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
    lateinit var userData :ProfileInfo
    var isJoined :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)

        val postJson = intent.getStringExtra(TeamSearchActivity.POST_DATA)
        val userJson = intent.getStringExtra(TeamSearchActivity.USER_DATA)
        postData = Gson().fromJson(postJson, Post::class.java)
        userData = Gson().fromJson(userJson, ProfileInfo::class.java)
        showPostInfo()



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

    private fun updateParticipants ()
    {
        isJoined = false
        textView_postView_participantsResult.text = ""
        for(user in postData.joinedUsers)
        {
            textView_postView_participantsResult.text = " ${textView_postView_participantsResult.text} ${user.username}"
            if(user.id == userData.id)
            {
                isJoined = true
            }
        }
        if(isJoined)
        {
            button_postView_join.setImageResource(R.mipmap.post_view_exit)
        }
        else
        {
            button_postView_join.setImageResource(R.mipmap.post_view_join)
        }
    }

    private fun showPostInfo()
    {
        textView_postView_descriptionResult.text = "${postData.description} "
        textView_postView_locationResult.text = "${postData.location} "
        textView_postView_sportTypeResult.text = "${postData.sport}"
        textView_postView_creatorResult.text = "${postData.authorName}"
        textView_postView_timeResult.text = "${postData.startTime}"
        updateParticipants()
    }

    private val successJoinPostResponse = Response.Listener <JSONObject>()
    {
        button_postView_join.setImageResource(R.mipmap.post_view_exit)
        postData.joinedUsers.add(userData)
        isJoined = !isJoined
        updateParticipants()
    }

    private val successLeavePostResponse = Response.Listener <JSONObject>()
    {
        button_postView_join.setImageResource(R.mipmap.post_view_join)
        postData.joinedUsers.remove(userData)
        isJoined = !isJoined
        updateParticipants()
    }
}