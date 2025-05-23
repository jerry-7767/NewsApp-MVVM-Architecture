package com.rishi.newsapp.data.Local

import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.Local.entity.SourceListTable
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    fun getArticles(): Flow<List<ArticleTable>>

    fun getSourcelist(): Flow<List<SourceListTable>>

    fun deleteAllAndInsertAllNews(articles: List<ArticleTable>)

    fun deleteandinsertallSourcelist(sourceListTable: List<SourceListTable>)

}