package com.example.newsFeed.noteFeed.presentation.noteFeed

import com.example.newsFeed.noteFeed.model.Note

data class State(val isLoading: Boolean, val notes: List<Note> = emptyList(), val error: Throwable? = null)