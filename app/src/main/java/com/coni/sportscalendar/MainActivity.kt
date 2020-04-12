package com.coni.sportscalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


//import com.facebook.FacebookSdk

class MainActivity : AppCompatActivity() {

    //"https://webhook.site/8281e487-7d68-4500-8b7b-4bf81cdb6278"
    private val baseUrl:String = "http://10.0.2.2:8000"

    private val errorResponse = Response.ErrorListener ()
    {
        error ->
        if(error.networkResponse != null)
        {
            val json:JSONObject = JSONObject(String(error.networkResponse.data))

            text_Response.text ="Error ${error.networkResponse.statusCode}: " + "${json.getString("data")}"
        }
        else
            text_Response.text = "${error.toString()}"
    }

    private val successResponse = Response.Listener <JSONObject>()
    {
            response -> text_Response.text = "NetworkResponse : ${response.toString()}"
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestBuilder = HttpRequestBuilder(this, baseUrl)

        button_Login.setOnClickListener()
        {
            text_Response.text = "Sending request to the server..."

            val registration :User = User( editText_Name.text.toString(), editText_Password.text.toString())
            val jsonString = Gson().toJson(registration)
            val jsonObj = JSONObject(jsonString)

            val request = requestBuilder.buildJsonRequest(Request.Method.POST, "/login", jsonObj, successResponse, errorResponse)

            HttpConnection.getInstance(this).addToRequestQueue(request)
        }

        button_register.setOnClickListener()
        {
            text_Response.text = "Sending request to the server..."

            val registration :UserRegistrationInformation = UserRegistrationInformation( editText_Name.text.toString(), editText_Password.text.toString(), editText_Password.text.toString())
            val jsonString = Gson().toJson(registration)
            val jsonObj = JSONObject(jsonString)

            val request = requestBuilder.buildJsonRequest(Request.Method.POST, "/account", jsonObj, successResponse, errorResponse)

            HttpConnection.getInstance(this).addToRequestQueue(request)
        }


    }
}
