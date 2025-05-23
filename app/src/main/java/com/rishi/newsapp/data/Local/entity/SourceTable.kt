package com.rishi.newsapp.data.Local.entity

import androidx.room.ColumnInfo

data class SourceTable (
    @ColumnInfo(name = "sourceId")
    val id: String?,
    @ColumnInfo(name = "sourceName")
    val name: String = ""

)