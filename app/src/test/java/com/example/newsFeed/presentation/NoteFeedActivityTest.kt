package com.example.newsFeed.presentation

import com.example.newsFeed.R
import com.example.newsFeed.noteFeed.presentation.noteFeed.NoteFeedActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NoteFeedActivityTest {

    lateinit var noteFeedActivity: NoteFeedActivity

    @Before
    fun setup() {
        noteFeedActivity = Robolectric.buildActivity(NoteFeedActivity::class.java)
            .create()
            .start()
            .get()
    }


    @Test
    fun `When MainActivity created, tool bar set to expected title`() {
        val supportActionBar = noteFeedActivity.supportActionBar
        Assert.assertEquals(noteFeedActivity.getString(R.string.app_name), supportActionBar?.title)
    }
}