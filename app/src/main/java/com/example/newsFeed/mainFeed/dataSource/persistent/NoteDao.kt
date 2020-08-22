package com.example.newsFeed.mainFeed.dataSource.persistent

import androidx.room.*
import com.example.newsFeed.mainFeed.data.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Note)

    @Update
    suspend fun updateNotes(vararg notes: Note)

    @Delete
    suspend fun deleteNotes(vararg notes: Note)

    @Query("SELECT * FROM note")
    suspend fun loadAllNotes(): List<Note>
}