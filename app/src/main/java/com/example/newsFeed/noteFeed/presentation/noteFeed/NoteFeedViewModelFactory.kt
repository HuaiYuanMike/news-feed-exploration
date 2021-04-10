package com.example.newsFeed.noteFeed.presentation.noteFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsFeed.NewsFeedApplication
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoteFeedViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: NoteFeedViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}