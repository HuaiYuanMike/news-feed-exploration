package com.example.newsFeed.noteFeed.presentation.noteFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsFeed.NewsFeedApplication
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainFeedViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: MainFeedViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}