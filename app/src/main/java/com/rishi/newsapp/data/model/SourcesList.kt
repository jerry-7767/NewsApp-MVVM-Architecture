package me.amitshekhar.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class SourcesList(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("language")
    val language: String = "",

    @SerializedName("country")
    val country: String = ""

)