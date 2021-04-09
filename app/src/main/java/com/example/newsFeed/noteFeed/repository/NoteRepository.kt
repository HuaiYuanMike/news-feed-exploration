package com.example.newsFeed.noteFeed.repository

import android.util.Log
import com.example.newsFeed.noteFeed.dataSource.network.RandomCatImageService
import com.example.newsFeed.noteFeed.dataSource.persistent.NoteDatabase
import com.example.newsFeed.noteFeed.model.CatImage
import com.example.newsFeed.noteFeed.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabase: NoteDatabase,
    private val randomCatImageService: RandomCatImageService
) {

    suspend fun retrieveAllNotes(): List<Note> = noteDatabase.getNoteDao().loadAllNotes()

    suspend fun insertNode(note: Note) {
        noteDatabase.getNoteDao().insertNotes(note)
        Log.d(this.javaClass.simpleName, "Insert note $note")
    }

    suspend fun deleteNote(note: Note) = noteDatabase.getNoteDao().deleteNotes(note)

    suspend fun getRandomCatImage(): CatImage = randomCatImageService.getRandomCatImage()
}