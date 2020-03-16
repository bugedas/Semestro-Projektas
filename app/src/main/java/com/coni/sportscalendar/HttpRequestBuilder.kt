package com.coni.sportscalendar

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class HttpRequestBuilder(context:Context, baseUrl:String)
{
    private val baseUrl = baseUrl
    private var defaultErrorResponse:Response.ErrorListener = getDefaultErrorResponse(context)

    private fun getDefaultErrorResponse(context: Context):Response.ErrorListener
    {
        return Response.ErrorListener{ error -> Toast.makeText(context.applicationContext, "Error: ${error.message}", Toast.LENGTH_LONG )}
    }

    fun buildJsonRequest (requestMethod:Int, urlParameters:String, data:JSONObject?, successfulResponse:Response.Listener<JSONObject>, errorResponse:Response.ErrorListener? = null):JsonObjectRequest
    {
        val errorResponse = errorResponse?: defaultErrorResponse

        return JsonObjectRequest(requestMethod, baseUrl + urlParameters, data, successfulResponse, errorResponse)
    }

    fun setErrorResponse(response:Response.ErrorListener)
    {
        defaultErrorResponse = response
    }

}