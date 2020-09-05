package com.example.newsFeed.mainFeed.repository

import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.dataSource.persistent.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val roomDatabase: AppDatabase) {

    fun retrieveAllNotes(): Flow<List<Note>> {
        return roomDatabase.getNoteDao().loadAllNotes()
    }

    suspend fun insertNode(note: Note) {
        roomDatabase.getNoteDao().insertNotes(note)
    }

    suspend fun deleteNote(note: Note) {
        roomDatabase.getNoteDao().deleteNotes(note)
    }
}