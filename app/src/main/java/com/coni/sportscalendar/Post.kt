package com.coni.sportscalendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
    @Expose(serialize = true)
    @SerializedName("Location")
    val location:String)
{

}