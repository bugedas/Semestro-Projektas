package com.coni.sportscalendar

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

class HttpConnection(context:Context)
{
    companion object
    {
        @Volatile
        private var instance: HttpConnection? = null
        //private var cookieManager: CookieManager? = null
        fun getInstance(context: Context) = instance ?:
        synchronized(this)
        {
            instance ?: HttpConnection(context).also { instance = it }

        }
    }

    val requestQueue: RequestQueue by lazy{ Volley.newRequestQueue(context.applicationContext) }

    fun <T> addToRequestQueue (req: Request<T>)
    {
        requestQueue.add(req)
    }

}