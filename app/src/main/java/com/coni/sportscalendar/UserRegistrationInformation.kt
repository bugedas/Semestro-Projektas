package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRegistrationInformation(
    @SerializedName("username")
    @Expose(serialize = true)
    val username:String,
    @SerializedName("password")
    @Expose(serialize = true)
    val password:String,
    @SerializedName("repeatPassword")
    @Expose(serialize = true)
    val repeatPassword:String)
{
}