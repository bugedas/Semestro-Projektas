package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_change_pass.*
import kotlinx.android.synthetic.main.activity_my_profile.*
import org.json.JSONArray
import org.json.JSONObject

class MyProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)



        Server.getInstance(this).getUserInfo(successFetchPostsResponse)


        myProfile_chPass.setOnClickListener(){

            val intent = Intent(this,ChangePass::class.java)
            startActivity(intent)

        }

        apply_changes.setOnClickListener(){

            val change :UserDescr = UserDescr( myProfile_description.text.toString())
            Server.getInstance(this).DescriptionChange(change, successChangePassResponse)

        }



    }

    private val successFetchPostsResponse = Response.Listener <JSONObject>()
    { response ->
        val jsonParser = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        Log.d("Acount", "NetworkResponse : ${response.toString()}")
        val data: AccountInfo = jsonParser.fromJson(response.toString(), AccountInfo::class.java)

        Log.d("Acount", "${response.toString()}")
        Log.d("Acount", "${data.username}")
        myProfile_name.text = data.username
        myProfile_email.text = data.email
        myProfile_gender.text = data.gender
        myProfile_description.setText(data.description)


    }

    private val successChangePassResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("ChangeUserDescr", "NetworkResponse : ${response.toString()}")
        finish()
    }


}