package com.example.newsFeed.presentation

import com.example.newsFeed.R
import com.example.newsFeed.noteFeed.presentation.noteFeed.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    lateinit var mainActivity: MainActivity

    @Before
    fun setup() {
        mainActivity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .get()
    }


    @Test
    fun `When MainActivity created, tool bar set to expected title`() {
        val supportActionBar = mainActivity.supportActionBar
        Assert.assertEquals(mainActivity.getString(R.string.app_name), supportActionBar?.title)
    }
}