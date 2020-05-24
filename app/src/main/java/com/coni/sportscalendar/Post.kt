package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = true)
    val id:Int,
    @SerializedName("CreatorID")
    @Expose(serialize = false, deserialize = true)
    val authorID:Int,
    @Expose(serialize = true)
    @SerializedName("Description")
    val description:String,
    @SerializedName("sport")
    @Expose(serialize = true, deserialize = true)
    val sport:String,
    @SerializedName("startTime")
    @Expose(serialize = true, deserialize = true)
    val startTime:String,
    @SerializedName("endTime")
    @Expose(serialize = true, deserialize = true)
    val endTime:String,
    @Expose(serialize = true)
    @SerializedName("Location")
    val location:String,
    @SerializedName("limit")
    @Expose(serialize = true, deserialize = true)
    val limit:Int)
{

}