package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @Expose(serialize = false, deserialize = false)
    val authorID:Int,
    @Expose(serialize = true)
    @SerializedName("Description")
    val description:String,
    @Expose(serialize = true)
    @SerializedName("Location")
    val location:String)
{

}