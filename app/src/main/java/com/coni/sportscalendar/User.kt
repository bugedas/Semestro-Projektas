package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")
    @Expose(serialize = false)
    val username:String,
    @SerializedName("password")
    @Expose(serialize = true)
    val password:String,
    @SerializedName("email")
    @Expose(serialize = true)
    val email:String)
{
}