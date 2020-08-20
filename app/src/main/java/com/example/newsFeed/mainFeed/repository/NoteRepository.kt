package com.example.newsFeed.mainFeed.repository

import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.dataSource.persistent.AppDatabase
import javax.inject.Inject

class NoteRepository @Inject constructor(private val roomDatabase: AppDatabase) {

    suspend fun retrieveAllNotes(): List<Note> {
        return roomDatabase.getNoteDao().loadAllNotes()
    }

    suspend fun insertNode(note: Note) {
        roomDatabase.getNoteDao().insertNotes(note)
    }
}