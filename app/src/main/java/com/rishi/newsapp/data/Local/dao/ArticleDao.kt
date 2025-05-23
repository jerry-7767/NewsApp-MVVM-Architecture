package com.rishi.newsapp.data.Local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.Local.entity.SourceListTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllNewslist(): Flow<List<ArticleTable>>

    @Query("SELECT * FROM sourcelist")
    fun getAllSourcelist(): Flow<List<SourceListTable>>

    @Insert
    fun insertAllNews(articles: List<ArticleTable>)

    @Insert
    fun insertSourcelist(sourceListTable: List<SourceListTable>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun insertAnddeleteAllNews(articles: List<ArticleTable>){
        deleteAll()
        return insertAllNews(articles)
    }

    @Query("DELETE FROM sourcelist")
    fun deleteSourcelist()

    @Transaction
    fun insertanddeleteSourcelist(sourceListTable: List<SourceListTable>){
        deleteSourcelist()
        return insertSourcelist(sourceListTable)
    }
}