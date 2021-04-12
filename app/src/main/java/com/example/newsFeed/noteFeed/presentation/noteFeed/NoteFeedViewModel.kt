package com.example.newsFeed.noteFeed.presentation.noteFeed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsFeed.noteFeed.usecase.NoteFeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteFeedViewModel @Inject constructor(private val noteFeedUseCase: NoteFeedUseCase) : ViewModel() {

    private val TAG = javaClass.simpleName

    private val changes: Channel<Change> = Channel()

    private val _states: MutableLiveData<State> = MutableLiveData(
        State(isLoading = false)
    )

    val states: LiveData<State> = _states


    init {
        viewModelScope.launch {
            for (change in changes) {
                // TODO remove the non-null assertion operator if possible
                _states.value = reducer(_states.value!!, change)
            }
        }
    }

    // Commented out for reference, utilize Flow provided by Room.
//    fun getAllNotes() = viewModelScope.launch(Dispatchers.Default) {
//        noteUseCase.retrieveAllNotes()
//            .catch { exception ->
//                Log.d(TAG, exception.message)
//                // TODO Error Handling
//            }
//            .collect { list ->
//                _notes.postValue(list)
//            }
//    }

    fun dispatchAction(action: Action) = viewModelScope.launch(Dispatchers.Unconfined) {
        // Start to produce event to the stream.
        Log.d("mikelog", "Running on thread ${Thread.currentThread().name}")
        changes.send(Change.Loading)
        when (action) {
            is Action.GetAllNotes -> {
                changes.send(noteFeedUseCase.retrieveAllNotes())
            }
            is Action.InsertNote -> {
                changes.send(noteFeedUseCase.insertNote(action.note))
            }
            is Action.DeleteNote -> {
                changes.send(noteFeedUseCase.deleteNote(action.note))
            }
        }
    }

    private fun reducer(oldState: State, change: Change): State {
        return when(change) {
            Change.Loading -> oldState.copy(isLoading = change.isLoading)
            is Change.AllNotesRetrieved -> oldState.copy(isLoading = change.isLoading, notes = change.notes)
            is Change.NoteInserted -> oldState.copy(isLoading = change.isLoading, notes = change.notes)
            is Change.NoteDeleted -> oldState.copy(isLoading = change.isLoading, notes = change.notes)
            is Change.Error -> oldState.copy(isLoading = change.isLoading, error = change.error)
        }
    }

}

// TODO Next Big features
// 2. DiffUtil for recyclerView
// 3. Swipe to remove
// 4. Drag to change position
// 5. MVI
// 6. Bitrise Test
// 67. Bitrise Code coverage

// TODO List to confirm
// 1. Kotlin coroutine launchers - launch(), async() - and withContext()
//      Kotlin running multiple coroutines concurrently
// 1.1 Kotlin CoroutineScope - ViewModel scope (V)
// 1.2 Kotlin coroutineScope suspend function - coordinate the child  (v)
// 1.3 Kotlin Coroutine Channels
// 2. ViewModel Android fragment ktx byViewModels() method (v)
// 3. Dependency injection or something else which remove the usage of ViewModelProvider and Factory
// 4. ViewModel SavedStateHandle
// 5. Paging
// 6. Retrofit - REST API - HTTP ?

// TODO Future
//  - Paging Library from Jetpack
// https://proandroiddev.com/exploring-paging-library-from-jetpack-c661c7399662