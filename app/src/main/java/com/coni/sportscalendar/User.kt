package com.coni.sportscalendar

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username") val username:String,
    @SerializedName("password") val password:String)
{
}