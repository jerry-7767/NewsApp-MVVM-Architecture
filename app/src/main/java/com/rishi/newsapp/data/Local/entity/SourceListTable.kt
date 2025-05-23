package com.rishi.newsapp.data.Local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sourcelist")
data class SourceListTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "source_id")
    val source_id: String?,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "url")
    val url: String = "",
    @ColumnInfo(name = "language")
    val language: String = "",
    @ColumnInfo(name = "country")
    val country: String = ""

)