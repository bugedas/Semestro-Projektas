package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

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
    @SerializedName("sport", alternate = ["Sport"])
    @Expose(serialize = true, deserialize = true)
    val sport:String,
    @SerializedName("startTime" , alternate = ["StartTime"])
    @Expose(serialize = true, deserialize = true)
    val startTime:String,
    @SerializedName("endTime", alternate = ["EndTime"])
    @Expose(serialize = true, deserialize = true)
    val endTime:String,
    @Expose(serialize = true)
    @SerializedName("Location")
    val location:String,
    @SerializedName("limit", alternate = ["Limit"])
    @Expose(serialize = true, deserialize = true)
    val limit:Int)
{
    @SerializedName("CreatorName")
    @Expose(serialize = false, deserialize = true)
    val authorName:String = ""
    @SerializedName("Users")
    @Expose(serialize = false, deserialize = true)
    val joinedUsers:ArrayList<ProfileInfo> = ArrayList()
}