package com.coni.sportscalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportActionBar?.title = "Registracija"

        button_register.setOnClickListener()
        {
            val registration :UserRegistrationInformation = UserRegistrationInformation( editText_registrationName.text.toString(), editText_registrationPassword.text.toString(), editText_registrationRepeatPassword.text.toString())
            Server.getInstance(this).sendRegisterRequest(registration, successRegisterResponse)
        }
    }

    private val successRegisterResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("RegisterActivity", "NetworkResponse : ${response.toString()}")
        finish()
    }
}