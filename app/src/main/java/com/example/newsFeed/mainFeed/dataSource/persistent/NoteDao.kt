package com.example.newsFeed.mainFeed.dataSource.persistent

import androidx.room.*
import com.example.newsFeed.mainFeed.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Note)

    @Update
    suspend fun updateNotes(notes: Note)

    @Delete
    suspend fun deleteNotes(notes: Note)

    @Query("SELECT * FROM note")
    suspend fun loadAllNotes(): List<Note>
}