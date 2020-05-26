package com.coni.sportscalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Response
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_post_owner_view.*
import kotlinx.android.synthetic.main.activity_post_view.*
import org.json.JSONObject

class PostOwnerViewActivity : AppCompatActivity() {
    lateinit var postData :Post
    lateinit var userData :ProfileInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_owner_view)

        val postJson = intent.getStringExtra(TeamSearchActivity.POST_DATA)
        val userJson = intent.getStringExtra(TeamSearchActivity.USER_DATA)
        postData = Gson().fromJson(postJson, Post::class.java)
        userData = Gson().fromJson(userJson, ProfileInfo::class.java)
        showPostInfo()


        button_postOwnerView_delete.setOnClickListener()
        {
            Server.getInstance(this).deletePost(postData.id, successDeletePostResponse)
        }


    }

    private val successDeletePostResponse = Response.Listener <JSONObject>()
    {
        finish()
    }

    private fun showPostInfo()
    {
        textView_postOwnerView_descriptionResult.text = "${postData.description} "
        textView_postOwnerView_locationResult.text = "${postData.location} "
        textView_postOwnerView_sportTypeResult.text = "${postData.sport}"
        textView_postOwnerView_creatorResult.text = "${postData.authorName}"
        //textView_postView_timeResult.text = "${postData.startTime}"
        val date = postData.startTime.split('T')
        val time = date[1].split(':')
        textView_postOwnerView_timeResult.text = "${date[0]} ${time[0]}:${time[1]}"
        showParticipants()
    }

    private fun showParticipants ()
    {
        textView_postOwnerView_participantsResult.text = ""
        for(user in postData.joinedUsers)
        {
            textView_postOwnerView_participantsResult.text = " ${textView_postOwnerView_participantsResult.text} ${user.username}"
        }
    }
}