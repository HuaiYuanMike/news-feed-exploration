package com.example.newsFeed.di

import android.content.Context
import androidx.room.Room
import com.example.newsFeed.mainFeed.dataSource.persistent.NOTE_DATABASE_NAME
import com.example.newsFeed.mainFeed.dataSource.persistent.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideApplicationContext() = context

    @Provides
    @Singleton
    fun provideRoomNoteDatabase() = Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE_NAME).build()
}