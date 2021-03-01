package com.example.newsFeed.mainFeed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainFeedComposeFragment : Fragment(){

    private lateinit var mainFeedViewModel: MainFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        // Invoke action
        mainFeedViewModel.getAllNotes()
    }

}