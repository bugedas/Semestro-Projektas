package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_change_pass.*
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject

class ChangePass : AppCompatActivity() {

    private var user : UserRegistrationInformation?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)


        changePass_Button.setOnClickListener(){

            val change :Password = Password( changePass_OldPass.text.toString(), changePass_NewPass.text.toString(), changePass_RepeatNewPass.text.toString())
            Server.getInstance(this).PasswordChange(change, successChangePassResponse)






        }

    }

    private val successChangePassResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("ChangePass", "NetworkResponse : ${response.toString()}")
        finish()
    }

}