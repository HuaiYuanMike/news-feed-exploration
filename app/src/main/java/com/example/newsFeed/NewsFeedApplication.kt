package com.example.newsFeed

import android.app.Application
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.newsFeed.noteFeed.di.AppComponent
import com.example.newsFeed.noteFeed.di.AppModule
import com.example.newsFeed.noteFeed.di.DaggerAppComponent
import com.example.newsFeed.noteFeed.workManager.DailyNoteInsertWorker
import java.util.concurrent.TimeUnit

class NewsFeedApplication : Application() {

    companion object {
        lateinit var instance: NewsFeedApplication
    }

    lateinit var appComponent: AppComponent

    private val DAILY_NOTE_WORK_REQUEST_NAME = BuildConfig.APPLICATION_ID + "dailynote"

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        instance = this

        val dailyNoteWorkRequest =
            PeriodicWorkRequestBuilder<DailyNoteInsertWorker>(2, TimeUnit.HOURS)
                .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            DAILY_NOTE_WORK_REQUEST_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            dailyNoteWorkRequest
        )
    }
}