package com.example.newsFeed.mainFeed.presentation

import com.example.newsFeed.mainFeed.model.Note

data class State(val isLoading: Boolean, val notes: List<Note> = emptyList(), val error: Throwable? = null)