package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class UserDescr (
    @SerializedName("description")
    @Expose(serialize = true, deserialize = true)
    val descr:String)
{

}