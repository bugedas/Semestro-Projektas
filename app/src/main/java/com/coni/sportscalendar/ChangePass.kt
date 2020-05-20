package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_change_pass.*
import kotlinx.android.synthetic.main.activity_my_profile.*
import org.json.JSONObject

class ChangePass : AppCompatActivity() {

    private var user : UserRegistrationInformation?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)


        changePass_Button.setOnClickListener(){

            Server.getInstance(this).getUserInfo(successFetchPostsResponse)

            if( changePass_OldPass.text.equals(user?.password)){
                val intent = Intent(this,MyProfile::class.java)
                startActivity(intent)
            }






        }

    }

    private val successFetchPostsResponse = Response.Listener <JSONObject>()
    { response ->
        val jsonParser = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        Log.d("Change pass", "NetworkResponse : ${response.toString()}")
        val data: UserRegistrationInformation = jsonParser.fromJson(response.toString(), UserRegistrationInformation::class.java)
        user = data

        Log.d("Change pass", "${response.toString()}")
        Log.d("Change pass", "${data.username}")



    }

}