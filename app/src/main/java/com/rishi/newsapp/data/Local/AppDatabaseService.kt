package com.rishi.newsapp.data.Local

import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.Local.entity.SourceListTable
import kotlinx.coroutines.flow.Flow

class AppDatabaseService constructor(private val appDatabase: AppDatabase) : DatabaseService {

    override fun getSourcelist(): Flow<List<SourceListTable>> {
        return appDatabase.articleDao().getAllSourcelist()
    }

    override fun getArticles(): Flow<List<ArticleTable>> {
        return appDatabase.articleDao().getAllNewslist()
    }

    override fun deleteAllAndInsertAllNews(articles: List<ArticleTable>) {
        appDatabase.articleDao().insertAnddeleteAllNews(articles)
    }

    override fun deleteandinsertallSourcelist(sourceListTable: List<SourceListTable>) {
        appDatabase.articleDao().insertanddeleteSourcelist(sourceListTable)
    }

}