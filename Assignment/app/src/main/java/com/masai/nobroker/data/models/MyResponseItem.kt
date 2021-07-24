package com.masai.nobroker.data.models


import com.google.gson.annotations.SerializedName

data class MyResponseItem(
    @SerializedName("image")
    val image: String,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("title")
    val title: String
)