package com.rishi.newsapp.data.model

import com.google.gson.annotations.SerializedName
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.data.model.SourcesList

data class NewsSourceResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val sources: List<SourcesList> = ArrayList()
)
