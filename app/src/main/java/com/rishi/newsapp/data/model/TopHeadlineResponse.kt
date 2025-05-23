package com.rishi.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class TopHeadlineResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<Article> = ArrayList()
)
/*fun TopHeadlineResponse.toTopheadlinetable(): TopHeadlineResponseTable{
    return TopHeadlineResponseTable(
        status = status,
        totalResults = totalResults,
        articles = articles.toArticleTable(),
    )
}*/

