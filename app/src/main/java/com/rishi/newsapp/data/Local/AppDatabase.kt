package com.rishi.newsapp.data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rishi.newsapp.data.Local.dao.ArticleDao
import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.Local.entity.SourceListTable

@Database(entities = [ArticleTable::class, SourceListTable::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}