package com.example.newsFeed.mainFeed.repository

import com.example.newsFeed.NewsFeedApplication
import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.dataSource.persistent.NoteDatabase
import javax.inject.Inject

class NoteRepository @Inject constructor(private val roomDatabase: NoteDatabase) {

    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    suspend fun retrieveAllNotes(): List<Note> {
        return roomDatabase.getNoteDao().loadAllNotes()
    }

    suspend fun insertNode(note: Note) {
        roomDatabase.getNoteDao().insertNotes(note)
    }
}