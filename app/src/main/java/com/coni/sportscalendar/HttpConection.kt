package com.coni.sportscalendar

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class HttpConection(context:Context)
{
    companion object
    {
        @Volatile
        private var instance: HttpConection? = null
        fun getInstance(context: Context) = instance ?:
        synchronized(this)
        {
            instance ?: HttpConection(context).also { instance = it }
        }
    }
    val requestQueue: RequestQueue by lazy{ Volley.newRequestQueue(context.applicationContext) }

    fun <T> addToRequestQueue (req: Request<T>)
    {
        requestQueue.add(req)
    }

}