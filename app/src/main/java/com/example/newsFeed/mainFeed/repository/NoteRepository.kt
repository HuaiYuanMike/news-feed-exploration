package com.example.newsFeed.mainFeed.repository

import android.util.Log
import com.example.newsFeed.mainFeed.dataSource.network.RandomCatImageApi
import com.example.newsFeed.mainFeed.dataSource.persistent.AppDatabase
import com.example.newsFeed.mainFeed.model.CatImage
import com.example.newsFeed.mainFeed.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val roomDatabase: AppDatabase,
    private val randomCatImageApi: RandomCatImageApi
) {

    suspend fun retrieveAllNotes(): List<Note> = roomDatabase.getNoteDao().loadAllNotes()

    suspend fun insertNode(note: Note) {
        roomDatabase.getNoteDao().insertNotes(note)
        Log.d(this.javaClass.simpleName, "Insert note $note")
    }

    suspend fun deleteNote(note: Note) = roomDatabase.getNoteDao().deleteNotes(note)

    suspend fun getRandomCatImage(): CatImage = randomCatImageApi.getRandomCatImage()
}