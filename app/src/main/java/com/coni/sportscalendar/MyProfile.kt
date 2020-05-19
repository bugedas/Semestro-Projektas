package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_my_profile.*
import org.json.JSONArray
import org.json.JSONObject

class MyProfile : AppCompatActivity() {
    private var user : UserRegistrationInformation?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)



        Server.getInstance(this).getUserInfo(successFetchPostsResponse)


        myProfile_chPass.setOnClickListener(){

            val intent = Intent(this,ChangePass::class.java)
            startActivity(intent)

        }



    }

    private val successFetchPostsResponse = Response.Listener <JSONObject>()
    { response ->
        val jsonParser = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        Log.d("Acount", "NetworkResponse : ${response.toString()}")
        val data: UserRegistrationInformation = jsonParser.fromJson(response.toString(), UserRegistrationInformation::class.java)
        user = data

        Log.d("Acount", "${response.toString()}")
        Log.d("Acount", "${data.username}")
        myProfile_name.text = data.username
        myProfile_email.text = data.email


    }

}