package com.coni.sportscalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Prisijungimas"

        button_Login.setOnClickListener()
        {
            val login :User = User( editText_Name.text.toString(), editText_Password.text.toString(), editText_Name.text.toString())
            Server.getInstance(this).sendLoginRequest(login, successLoginResponse)
        }

        textView_registrationPage.setOnClickListener()
        {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

    private val errorResponse = Response.ErrorListener ()
    {
        error ->
        if(error.networkResponse != null)
        {
            val json:JSONObject = JSONObject(String(error.networkResponse.data))

            Log.d("LoginActivity", "Error ${error.networkResponse.statusCode}: " + "${json.getString("data")}" )
        }
        else
            Log.d("LoginActivity","${error.toString()}")
    }

    private val successLoginResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("LoginActivity", "NetworkResponse : ${response.toString()}")
        val intent = Intent(this, TeamSearchActivity::class.java)
        //TODO: Stop remaining network requests for this layout
        //HttpConnection.getInstance(this).requestQueue.stop()
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        // PVZ: kaip perduoti duomenis i kita activity
        //intent.putExtra(USER_NAME, editText_Name.text.toString())
        startActivity(intent)
    }
}
