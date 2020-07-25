package com.example.newsFeed.mainFeed.dataSource.persistent

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsFeed.mainFeed.data.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}