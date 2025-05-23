package com.rishi.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.rishi.newsapp.data.Local.entity.ArticleTable

data class Article(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val source: Source
)

fun Article.toArticleTable(): ArticleTable {
    return ArticleTable(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = source.toSourceTable(),
    )
}

fun List<Article>.toArticleTable(): List<ArticleTable> {
    return this.map { it.toArticleTable() }
}