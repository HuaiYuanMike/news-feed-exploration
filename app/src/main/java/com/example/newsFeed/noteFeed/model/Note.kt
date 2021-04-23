package com.example.newsFeed.noteFeed.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "author") val author: String = "",
    @ColumnInfo(name = "content") val content: String = "",
    @ColumnInfo(name = "imageUri") val imageUri: String = "",
    @ColumnInfo(name = "time_stamp") val timestamp: Long = 0)