package com.example.newsFeed.noteFeed.workManager

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.newsFeed.NewsFeedApplication
import com.example.newsFeed.noteFeed.di.DaggerAppComponent
import com.example.newsFeed.noteFeed.model.Note
import com.example.newsFeed.noteFeed.repository.NoteFeedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

// TODO Extend CoroutineWorker?
class DailyNoteInsertWorker(appContext: Context, workerParameters: WorkerParameters): Worker(appContext, workerParameters) {

    @Inject
    lateinit var noteFeedRepository: NoteFeedRepository

    private val TITLE = "Regular System Check"

    private val AUTHOR = this.javaClass.simpleName

    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    override fun doWork(): Result {
        Log.d(javaClass.simpleName, "The periodically scheduled work is executing")

        val connectionManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val content = "System check at " + Calendar.getInstance().time.toString() + ". " +
                "Network connection status: " +
                (connectionManager.activeNetworkInfo?.isConnectedOrConnecting ?: " No connection.")

        runBlocking {
            noteFeedRepository.insertNode(note = Note(title = TITLE, author = AUTHOR, content = content))
        }

        return Result.success()
    }
}