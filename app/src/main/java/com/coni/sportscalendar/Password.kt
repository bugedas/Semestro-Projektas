package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Password (
    @SerializedName("password")
    @Expose(serialize = true, deserialize = true)
    val pass:String,
    @SerializedName("newPassword")
    @Expose(serialize = true, deserialize = true)
    val newPass:String,
    @SerializedName("newPasswordRepeat")
    @Expose(serialize = true, deserialize = true)
    val newPassRep:String)
{

}