package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Response
import org.json.JSONObject

class AuthorisationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorisation)

        checkIfUserIsLoggedIn()
    }

    private fun checkIfUserIsLoggedIn ()
    {

        Server.getInstance(this).checkIfAccIsSignedIn(onSuccess, onFailed)

    }

    private val onFailed = Response.ErrorListener ()
    {
        val intent = Intent(this,LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    private val onSuccess = Response.Listener <JSONObject>()
    {
        val intent = Intent(this,TeamSearchActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        //authorised = true
    }
}