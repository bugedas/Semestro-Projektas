package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountInfo(
    @SerializedName("username", alternate = ["Username"])
    @Expose(serialize = true, deserialize = true)
    val username:String,
    @SerializedName("email", alternate = ["Email"])
    @Expose(serialize = true, deserialize = true)
    val email:String,
    @SerializedName("gender", alternate = ["Gender"])
    @Expose(serialize = true, deserialize = true)
    val gender:String,
    @SerializedName("description", alternate = ["Description"])
    @Expose(serialize = true, deserialize = true)
    val description:String
    )
{
}