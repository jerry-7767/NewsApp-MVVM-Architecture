package com.rishi.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.rishi.newsapp.data.Local.entity.SourceListTable

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

fun SourcesList.toSourcelistTable(): SourceListTable {
    return SourceListTable(
        source_id = id,
        name = name,
        url = url,
        language = language,
        country = country
    )
}