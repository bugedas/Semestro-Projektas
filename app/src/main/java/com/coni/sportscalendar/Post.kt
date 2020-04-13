package com.coni.sportscalendar

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("creator") val authorID:Int,
    @SerializedName("description") val description:String,
    @SerializedName("location") val location:String)
{

}