package com.rishi.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.rishi.newsapp.data.Local.entity.SourceTable

data class Source(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
)
fun Source.toSourceTable(): SourceTable {
    return SourceTable(id, name)
}