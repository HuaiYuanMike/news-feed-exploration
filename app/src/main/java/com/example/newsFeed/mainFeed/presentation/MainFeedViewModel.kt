package com.example.newsFeed.mainFeed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsFeed.mainFeed.data.Note

class MainFeedViewModel : ViewModel() {

    private val innerLiveData: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = innerLiveData
}