package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @Expose(serialize = false)
    val authorID:Int,
    @Expose(serialize = true)
    @SerializedName("description")
    val description:String,
    @Expose(serialize = true)
    @SerializedName("location")
    val location:String)
{

}