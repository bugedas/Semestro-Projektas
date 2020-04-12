package com.coni.sportscalendar

import com.google.gson.annotations.SerializedName

data class UserRegistrationInformation(
    @SerializedName("username") val username:String,
    @SerializedName("password") val password:String,
    @SerializedName("repeatPassword") val repeatPassword:String)
{
}