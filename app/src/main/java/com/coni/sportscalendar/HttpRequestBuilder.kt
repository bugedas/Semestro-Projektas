package com.coni.sportscalendar

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HttpRequestBuilder(context:Context, baseUrl:String)
{
    private val baseUrl = baseUrl
    private var defaultErrorResponse:Response.ErrorListener = getDefaultErrorResponse(context)

    private fun getDefaultErrorResponse(context: Context):Response.ErrorListener
    {
        val response = Response.ErrorListener ()
        { error ->
            if (error.networkResponse != null) {
                //TODO: maybe to better error handling https://stackoverflow.com/questions/24700582/handle-volley-error
                try
                {
                    val json: JSONObject = JSONObject(String(error.networkResponse.data))

                    Toast.makeText(
                        context.applicationContext,
                        "Error ${error.networkResponse.statusCode}: " + "${json.getString("data")}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                catch (e: JSONException)
                {
                    Toast.makeText(
                        context.applicationContext,
                        "Error ${error.networkResponse.statusCode}: ",
                        Toast.LENGTH_LONG
                    ).show()
                }


            } else
                Toast.makeText(context.applicationContext, "${error.toString()}", Toast.LENGTH_LONG).show()
        }

        return response
    }

    fun buildJsonRequest (requestMethod:Int, urlParameters:String, data:JSONObject?, successfulResponse:Response.Listener<JSONObject>, errorResponse:Response.ErrorListener? = null):JsonObjectRequest
    {
        val errorResponse = errorResponse?: defaultErrorResponse

        return JsonObjectRequest(requestMethod, baseUrl + urlParameters, data, successfulResponse, errorResponse)
    }

    fun buildJsonArrayRequest (requestMethod:Int, urlParameters:String, data:JSONArray?, successfulResponse:Response.Listener<JSONArray>, errorResponse:Response.ErrorListener? = null):JsonArrayRequest
    {
        val errorResponse = errorResponse?: defaultErrorResponse

        return JsonArrayRequest(requestMethod, baseUrl + urlParameters, data, successfulResponse, errorResponse)
    }


    fun setErrorResponse(response:Response.ErrorListener)
    {
        defaultErrorResponse = response
    }

}