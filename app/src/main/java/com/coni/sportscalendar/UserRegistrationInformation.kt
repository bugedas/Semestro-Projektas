package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRegistrationInformation(
    @SerializedName("username", alternate = ["Username"])
    @Expose(serialize = true, deserialize = true)
    val username:String,
    @SerializedName("password", alternate = ["Password"])
    @Expose(serialize = true, deserialize = false)
    val password:String,
    @SerializedName("repeatPassword")
    @Expose(serialize = true)
    val repeatPassword:String,
    @SerializedName("email", alternate = ["Email"])
    @Expose(serialize = true, deserialize = true)
    val email:String)
{
}