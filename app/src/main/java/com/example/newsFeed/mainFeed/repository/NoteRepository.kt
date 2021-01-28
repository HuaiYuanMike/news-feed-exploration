package com.example.newsFeed.mainFeed.repository

import com.example.newsFeed.mainFeed.dataSource.network.RandomCatImageApi
import com.example.newsFeed.mainFeed.model.Note
import com.example.newsFeed.mainFeed.dataSource.persistent.AppDatabase
import com.example.newsFeed.mainFeed.model.CatImage
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class NoteRepository @Inject constructor(private val roomDatabase: AppDatabase, private val randomCatImageApi: RandomCatImageApi) {

    fun retrieveAllNotes(): Flow<List<Note>> = roomDatabase.getNoteDao().loadAllNotes()

    suspend fun insertNode(note: Note) = roomDatabase.getNoteDao().insertNotes(note)

    suspend fun deleteNote(note: Note) = roomDatabase.getNoteDao().deleteNotes(note)

    suspend fun getRandomCatImage(): CatImage = randomCatImageApi.getRandomCatImage()

}