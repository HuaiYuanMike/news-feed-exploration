package com.example.newsFeed.mainFeed.dataSource.persistent

import androidx.room.*
import com.example.newsFeed.mainFeed.data.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(vararg notes: Note)

    @Update
    fun updateNotes(vararg notes: Note)

    @Delete
    fun deleteNotes(vararg notes: Note)

    @Query("SELECT * FROM note")
    fun loadAllNotes(): List<Note>
}