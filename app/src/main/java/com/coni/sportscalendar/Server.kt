package com.coni.sportscalendar

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.google.gson.Gson
import org.json.JSONObject
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import com.google.gson.GsonBuilder
import org.json.JSONArray


class Server private constructor(private val context: Context)
{
    private val serverUrl :String = "http://10.0.2.2:8000"
    private val loginPath :String = "/login"
    private val registerPath :String = "/account"
    private val postPath :String = "/events"
    private val requestBuilder = HttpRequestBuilder(context, serverUrl)
    private var jasonParser: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    companion object
    {
        @Volatile
        private var instance: Server? = null
        private var cookieManager: CookieManager = CookieManager(null, CookiePolicy.ACCEPT_ALL)
        fun getInstance(context: Context) = instance ?:
        synchronized(this)
        {
            if(instance == null)
                CookieHandler.setDefault(cookieManager)
            instance ?: Server(context).also { instance = it }
        }
    }

    fun resetCookies()
    {
        cookieManager.cookieStore.removeAll();
    }

    fun checkIfAccIsSignedIn (onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonRequest(Request.Method.GET, "$loginPath", null, onSuccess, onFail)

        Log.d("Server", "Singing out user from server")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun requestLogOut (onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonRequest(Request.Method.DELETE, "$loginPath", null, onSuccess, onFail)

        Log.d("Server", "Singing out user from server")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun getPosts (onSuccess: Response.Listener <JSONArray>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonArrayRequest(Request.Method.GET, "$postPath", null, onSuccess, onFail)

        Log.d("Server", "Getting all posts from server")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun getPosts (location :String, onSuccess: Response.Listener <JSONArray>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonArrayRequest(Request.Method.GET, "$postPath?location=$location", null, onSuccess, onFail)

        Log.d("Server", "Getting posts from server with location =  $location")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun getPosts (creatorID :Int, onSuccess: Response.Listener <JSONArray>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonArrayRequest(Request.Method.GET, "$postPath?creatorID=$creatorID", null, onSuccess, onFail)

        Log.d("Server", "Getting posts from server with creator id =  $creatorID")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun getPosts (creatorID :Int, location :String,  onSuccess: Response.Listener <JSONArray>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonArrayRequest(Request.Method.GET, "$postPath?creatorID=$creatorID&location=$location", null, onSuccess, onFail)

        Log.d("Server", "Getting posts from server with creator =  $creatorID and location = $location")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun leavePost (eventID :Int,  onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonRequest(Request.Method.DELETE, "$postPath/$eventID/users", null, onSuccess, onFail)

        Log.d("Server", "Joining event. Event id: $eventID")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun joinPost (eventID :Int,  onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonRequest(Request.Method.PATCH, "$postPath/$eventID/users", null, onSuccess, onFail)

        Log.d("Server", "Joining event. Event id: $eventID")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun createPost (postInfo :Post, onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        val jsonObj = JSONObject(jasonParser.toJson(postInfo))
        val request = requestBuilder.buildJsonRequest(Request.Method.POST, postPath, jsonObj, onSuccess, onFail)

        Log.d("Server", "Sending request to create a post: ${jsonObj.toString()}")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun sendLoginRequest (loginInfo: User, onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        // Converting data to json object
        val jsonObj = JSONObject(jasonParser.toJson(loginInfo))
        val request = requestBuilder.buildJsonRequest(Request.Method.POST, loginPath, jsonObj, onSuccess, onFail)

        Log.d("Server", "Sending login request to the server: ${jsonObj.toString()}")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

    fun sendRegisterRequest (registerInfo: UserRegistrationInformation, onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        // Converting data to json object
        val jsonObj = JSONObject(jasonParser.toJson(registerInfo))
        val request = requestBuilder.buildJsonRequest(Request.Method.POST, registerPath, jsonObj, onSuccess, onFail)

        Log.d("Server", "Sending registration request to the server: ${jsonObj.toString()}")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }


    fun getUserInfo (onSuccess: Response.Listener <JSONObject>, onFail : Response.ErrorListener? = null)
    {
        val request = requestBuilder.buildJsonRequest(Request.Method.GET, registerPath, null, onSuccess, onFail)

        Log.d("Server", "Getting user information")
        HttpConnection.getInstance(context).addToRequestQueue(request)
    }

}