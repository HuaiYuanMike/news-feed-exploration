package com.example.newsFeed.noteFeed.dataSource.persistent

import androidx.room.*
import com.example.newsFeed.noteFeed.model.Note

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