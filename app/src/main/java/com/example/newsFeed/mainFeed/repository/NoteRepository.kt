package com.example.newsFeed.mainFeed.repository

import android.content.Context
import androidx.room.Room
import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.dataSource.persistent.NOTE_DATABASE_NAME
import com.example.newsFeed.mainFeed.dataSource.persistent.NoteDatabase

class NoteRepository(context: Context) {

    //TODO Dependency injection
    private val roomDatabase = Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE_NAME).build()


    suspend fun retrieveAllNotes(): List<Note> {
        return roomDatabase.getNoteDao().loadAllNotes()
    }

    suspend fun insertNode(note: Note) {
        roomDatabase.getNoteDao().insertNotes(note)
    }
}